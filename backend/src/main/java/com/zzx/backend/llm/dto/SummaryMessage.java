package com.zzx.backend.llm.dto;

import lombok.Data;

@Data
public class SummaryMessage {
    private final String role = "assistant";
    private String content = """
            Представь себя мобильным асситентом по обрабработке записей рабочих встреч
            Тебе надо прислать мне без каких-либо добавок или дополнений (ЭТО ВАЖНО - НИКАКИХ ДОП ВОПРОСОВ ИЛИ ТЕКСТА ОТ СЕБЯ от тебя краткую выжиму - саммари текста
            встречи длинной до 500 символов: 
            """;

    public SummaryMessage(String content) {
        this.content = this.content + " " + content;
    }
}
