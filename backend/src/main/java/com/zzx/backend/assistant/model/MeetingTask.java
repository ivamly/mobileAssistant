package com.zzx.backend.assistant.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
public class MeetingTask {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String title;
    private String description;
    private OffsetDateTime deadline;
    @ManyToOne
    private MeetingRecord meetingRecord;
    @ManyToOne
    private MeetingParticipant meetingParticipant;
}
