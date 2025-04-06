package com.zzx.backend.asr.parser;

import com.zzx.backend.asr.dto.Line;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TranscriptParser {

    public List<Line> parse(String data) {
        JSONArray jsonArray = new JSONArray();

        // Разделяем входные данные на строки
        String[] lines = data.split("\n");
        String currentSpeaker = null;
        StringBuilder currentQuote = new StringBuilder();

        for (String line : lines) {
            if (line.trim().isEmpty()) {
                continue;
            }

            if (line.contains("-->")) {
                // Если есть текущая цитата, добавляем её в массив
                if (currentSpeaker != null) {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("speaker", currentSpeaker);
                    jsonObject.put("quote", currentQuote.toString().trim());
                    jsonArray.put(jsonObject);
                    currentQuote.setLength(0); // Очищаем текущую цитату
                }
            } else if (line.startsWith("speaker_")) {
                // Если строка начинается с "speaker_", сохраняем говорящего
                String[] parts = line.split(":");
                if (parts.length > 1) {
                    currentSpeaker = parts[0].trim();
                    currentQuote.append(parts[1].trim()).append(" "); // Добавляем цитату
                }
            } else {
                // Если это продолжение цитаты, добавляем к текущей
                currentQuote.append(line.trim()).append(" ");
            }
        }

        // Добавляем последнюю цитату, если она есть
        if (currentSpeaker != null) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("speaker", currentSpeaker);
            jsonObject.put("quote", currentQuote.toString().trim());
            jsonArray.put(jsonObject);
        }

        // Возвращаем результат в виде строки JSON
        return convert(jsonArray.toString(2)); // Форматированный вывод JSON
    }

    public List<Line> convert(String jsonData) {
        List<Line> lines = new ArrayList<>();

        try {
            JSONArray jsonArray = new JSONArray(jsonData);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                Line line = new Line();
                line.setSpeaker(jsonObject.getString("speaker"));
                line.setQuote(jsonObject.getString("quote"));

                lines.add(line);
            }
        } catch (Exception e) {
            System.out.println("Ошибка при разборе JSON: " + e.getMessage());
        }

        return lines;
    }
}