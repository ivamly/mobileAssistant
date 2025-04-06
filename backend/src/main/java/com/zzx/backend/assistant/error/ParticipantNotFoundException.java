package com.zzx.backend.assistant.error;

public class ParticipantNotFoundException extends RuntimeException {

    public ParticipantNotFoundException(String id) {
        super("Участник с id - " + id + " не найден");
    }
}
