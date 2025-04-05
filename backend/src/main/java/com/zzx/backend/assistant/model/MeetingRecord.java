package com.zzx.backend.assistant.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;

import java.io.File;
import java.time.OffsetDateTime;
import java.util.Set;
import java.util.UUID;

@Entity
public class MeetingRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String title;
    private File content; // audio file
    private OffsetDateTime meetingTime;
    @OneToOne(mappedBy = "meetingRecord")
    private MeetingTranscription meetingTranscription;
    @OneToOne(mappedBy = "meetingRecord")
    private MeetingSummary meetingSummary;
    @ManyToMany
    @JoinTable(
            name = "meeting_record_meeting_participants",
            joinColumns = @JoinColumn(name = "meeting_record_id"),
            inverseJoinColumns = @JoinColumn(name = "meeting_paticipants_id")
    )
    private Set<MeetingParticipant> meetingParticipants;
}
