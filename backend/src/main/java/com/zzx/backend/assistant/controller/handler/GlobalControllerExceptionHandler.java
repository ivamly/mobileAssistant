package com.zzx.backend.assistant.controller.handler;

import com.zzx.backend.assistant.dto.rs.RecordWithSummaryRs;
import com.zzx.backend.assistant.error.ParticipantNotFoundException;
import com.zzx.backend.assistant.error.UiViewException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Collection;
import java.util.List;

@ControllerAdvice
public class GlobalControllerExceptionHandler {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(UiViewException.class)
    public Collection<RecordWithSummaryRs> UiException() {
        return List.of(new RecordWithSummaryRs());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ParticipantNotFoundException.class)
    public String ParticipantNotFoundException(ParticipantNotFoundException ex) {
        return ex.getMessage();
    }
}
