package com.zzx.backend.assistant.exception;

public class ParticipantNotFoundException extends RuntimeException {

    public ParticipantNotFoundException(String id) {
        super("Участник с id - " + id + " не найден");
    }
}
