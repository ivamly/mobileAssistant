package com.zzx.backend.common.dto;

import java.util.Set;

public class MeetingParticipantData {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private Set<MeetingRecordData> meetingRecord;
}
