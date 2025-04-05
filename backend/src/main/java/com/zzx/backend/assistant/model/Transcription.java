package com.zzx.backend.assistant.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

import java.util.UUID;

@Entity
public class Transcription {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String transcription;
    @OneToOne
    private Record record;
}
