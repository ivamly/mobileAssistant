package com.zzx.backend.llm.dto;

import lombok.Data;

@Data
public class TaskMessage {
    private final String role = "assistant";
    private String content = """
            Представь себя мобильным асситентом по обрабработке записей рабочих встреч
            Если на встрече обуждали какие-то задачи или обязанности - пришли список задач где указано
            имя исполнителя, срок дедлайна, название и описание задачи
            если на встрече задачи не обсуждались, пришли пустой ответ 
            Учти, что не должно быть никаких доп вопросов или добавок от тебя
            """;

    public TaskMessage(String content) {
        this.content = this.content + " " + content;
    }
}
