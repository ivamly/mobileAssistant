package com.zzx.backend.assistant.exception;

public class RecordNotFoundException extends RuntimeException{

    public RecordNotFoundException(String id) {
        super("Запись с id - " + id + " не найдена");
    }
}
