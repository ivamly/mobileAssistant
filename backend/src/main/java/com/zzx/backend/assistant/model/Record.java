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
public class Record {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String title;
    private File content; // audio/video file?
    private OffsetDateTime meetingTime;
    @OneToOne(mappedBy = "record")
    private Transcription transcription;
    @OneToOne(mappedBy = "record")
    private Summary summary;
    @ManyToMany
    @JoinTable(
            name = "record_participants",
            joinColumns = @JoinColumn(name = "record_id"),
            inverseJoinColumns = @JoinColumn(name = "participants_id")
    )
    private Set<Participant> participants;
}
