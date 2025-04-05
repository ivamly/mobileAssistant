package com.zzx.backend.assistant.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

import java.util.UUID;

@Entity
public class MeetingTranscription {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String meetingTranscription;
    @OneToOne
    private MeetingRecord meetingRecord;
}
