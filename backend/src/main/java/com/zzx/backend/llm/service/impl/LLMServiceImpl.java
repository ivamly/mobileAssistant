package com.zzx.backend.llm.service.impl;

import com.zzx.backend.common.dto.SummaryData;
import com.zzx.backend.common.dto.TaskData;
import com.zzx.backend.common.dto.TranscriptionData;
import com.zzx.backend.llm.dto.SummaryRequest;
import com.zzx.backend.llm.dto.TaskRequest;
import com.zzx.backend.llm.service.LLMService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@Service
public class LLMServiceImpl implements LLMService {

    public static final String URL = "https://ai.rt.ru/api/1.0/gigachat/chat";

    @Value("${llm.token}")
    private String TOKEN;

    @Override
    public SummaryData getSummary(TranscriptionData transcriptionData) {
        // Настройка заголовков
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(TOKEN);
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Создание объекта HttpEntity
        HttpEntity<SummaryRequest> entity = new HttpEntity<>(new SummaryRequest(transcriptionData.getTranscription()), headers);

        // Отправка POST-запроса
        RestTemplate restTemplate = new RestTemplate();
        try {
            String response = restTemplate.postForObject(URL, entity, String.class);

            log.info(response); // Выводим результат
        } catch (Exception e) {
            e.printStackTrace(); // Обработка ошибок
        }

        return new SummaryData();
    }

    @Override
    public List<TaskData> searchTasks(TranscriptionData transcriptionData) {
        // Настройка заголовков
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(TOKEN);
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Создание объекта HttpEntity
        HttpEntity<TaskRequest> entity = new HttpEntity<>(new TaskRequest(transcriptionData.getTranscription()), headers);

        // Отправка POST-запроса
        RestTemplate restTemplate = new RestTemplate();
        try {
            String response = restTemplate.postForObject(URL, entity, String.class);

            log.info(response); // Выводим результат
        } catch (Exception e) {
            e.printStackTrace(); // Обработка ошибок
        }

        return List.of();
    }
}
