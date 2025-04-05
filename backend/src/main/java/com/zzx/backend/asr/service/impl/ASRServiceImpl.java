package com.zzx.backend.asr.service.impl;

import com.zzx.backend.asr.dto.Request;
import com.zzx.backend.asr.parser.TranscriptParser;
import com.zzx.backend.asr.service.ASRService;
import com.zzx.backend.common.dto.RecordData;
import com.zzx.backend.common.dto.TranscriptionData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class ASRServiceImpl implements ASRService {

    private static final String API_URL = "https://ai.rt.ru/api/1.0/whisper/audio";
    private static final String DOWNLOAD_ENDPOINT = "https://ai.rt.ru/api/1.0/download";
    private static final String BEARER_TOKEN = "eyJhbGciOiJIUzM4NCJ9.eyJzY29wZXMiOlsid2hpc3BlciJdLCJzdWIiOiJoazEiLCJpYXQiOjE3NDI0Nzg4MzIsImV4cCI6MjYwNjM5MjQzMn0.u8TRrUoMw_8BgmMBCmJJZLZSFeb_MLorM34SZPS32C_8QOs40nqc5EHv1kxornDq";
    private final TranscriptParser transcriptParser;

    @Override
    public TranscriptionData getTranscription(RecordData recordData) {
        try {
            File file = recordData.getContent();
            Resource resource = new FileSystemResource(file);
            MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
            body.add("request", new Request());
            body.add("file", resource);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            headers.setBearerAuth(BEARER_TOKEN);

            HttpEntity<MultiValueMap<String, Object>> requestEntity =
                    new HttpEntity<>(body, headers);

            ResponseEntity<String> response = new RestTemplate().postForEntity(API_URL, requestEntity, String.class);

            if (response.getStatusCode().is2xxSuccessful()) {
                String answer = response.getBody();
                log.info(answer);
                return new TranscriptionData();
            } else {
                throw new RuntimeException("Ошибка выполнения запроса: " + response.getStatusCode());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String downloadAttachment(String id) {
        RestTemplate restTemplate = new RestTemplate();

        Map<String, String> params = new HashMap<>();
        params.put("id", id);
        params.put("serviceType", "whisper");
        params.put("role", "assistant");

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(BEARER_TOKEN);

        HttpEntity<Void> entity = new HttpEntity<>(headers);
        String requestUrl = UriComponentsBuilder.fromHttpUrl(DOWNLOAD_ENDPOINT)
                .queryParam("id", id)
                .queryParam("serviceType", "whisper")
                .queryParam("role", "assistant")
                .toUriString();

        ResponseEntity<String> response = restTemplate.exchange(requestUrl, HttpMethod.GET, entity, String.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            String correctedText = new String(response.getBody().getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
            String json = transcriptParser.parse(correctedText);
            log.info("JSON " + json);
            return json;
        } else {
            throw new RuntimeException("Ошибка загрузки вложения: " + response.getStatusCode());
        }
    }
}