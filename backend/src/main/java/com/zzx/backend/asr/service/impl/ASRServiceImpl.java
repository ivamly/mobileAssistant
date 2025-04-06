package com.zzx.backend.asr.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zzx.backend.asr.dto.AsrRequest;
import com.zzx.backend.asr.dto.AsrResponse;
import com.zzx.backend.asr.dto.Line;
import com.zzx.backend.asr.parser.TranscriptParser;
import com.zzx.backend.asr.service.ASRService;
import com.zzx.backend.common.dto.ASRAggregateData;
import com.zzx.backend.common.dto.ParticipantData;
import com.zzx.backend.common.dto.RecordData;
import com.zzx.backend.common.dto.TranscriptionData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
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
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ASRServiceImpl implements ASRService {

    private static final String API_URL = "https://ai.rt.ru/api/1.0/whisper/audio";
    private static final String DOWNLOAD_ENDPOINT = "https://ai.rt.ru/api/1.0/download";
    @Value("${asr.token}")
    private String TOKEN;
    private final TranscriptParser transcriptParser;
    private final ObjectMapper objectMapper;

    @Override
    public ASRAggregateData getTranscription(RecordData recordData) {
        try {

            ASRAggregateData asrAggregateData = new ASRAggregateData();
            File file = recordData.getContent();
            Resource resource = new FileSystemResource(file);
            MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
            body.add("request", new AsrRequest());
            body.add("file", resource);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            headers.setBearerAuth(TOKEN);

            HttpEntity<MultiValueMap<String, Object>> requestEntity =
                    new HttpEntity<>(body, headers);

            ResponseEntity<AsrResponse[]> response = new RestTemplate().postForEntity(API_URL, requestEntity, AsrResponse[].class);

            if (response.getStatusCode().is2xxSuccessful()) {
                AsrResponse asrResponse = response.getBody()[0];
                int id = asrResponse.getMessage().getId();
                String json = downloadAttachment(id, asrAggregateData, recordData.getId());
                TranscriptionData td = new TranscriptionData();
                td.setTranscription(json);
                td.setRecordId(recordData.getId());
                asrAggregateData.setTranscription(td);
                return asrAggregateData;
            } else {
                throw new RuntimeException("Ошибка выполнения запроса: " + response.getStatusCode());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String downloadAttachment(int id, ASRAggregateData asrAggregateData, String recordId) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();

        Map<String, String> params = new HashMap<>();
        params.put("id", String.valueOf(id));
        params.put("serviceType", "whisper");
        params.put("role", "assistant");

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(TOKEN);

        HttpEntity<Void> entity = new HttpEntity<>(headers);
        String requestUrl = UriComponentsBuilder.fromHttpUrl(DOWNLOAD_ENDPOINT)
                .queryParam("id", id)
                .queryParam("serviceType", "whisper")
                .queryParam("role", "assistant")
                .toUriString();

        ResponseEntity<String> response = restTemplate.exchange(requestUrl, HttpMethod.GET, entity, String.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            String correctedText = new String(response.getBody().getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
            List<Line> lines = transcriptParser.parse(correctedText);
            Set<ParticipantData> participants = getParticipants(lines);
            participants.forEach(participant -> participant.setRecordId(recordId));
            asrAggregateData.setParticipants(participants);
            log.info("JSON {}", objectMapper.writeValueAsString(lines));
            return objectMapper.writeValueAsString(lines);
        } else {
            throw new RuntimeException("Ошибка загрузки вложения: " + response.getStatusCode());
        }
    }

    private Set<ParticipantData> getParticipants(List<Line> lines) {
        return lines.parallelStream()
                .map(Line::getSpeaker)
                .map(this::map)
                .collect(Collectors.toSet());
    }

    private ParticipantData map(String name) {
        return ParticipantData.builder()
                .name(name)
                .build();
    }
}