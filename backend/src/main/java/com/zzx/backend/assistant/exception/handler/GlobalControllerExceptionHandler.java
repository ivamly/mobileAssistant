package com.zzx.backend.assistant.exception.handler;

import com.zzx.backend.assistant.dto.rs.RecordWithSummaryRs;
import com.zzx.backend.assistant.exception.FileParseException;
import com.zzx.backend.assistant.exception.ParticipantNotFoundException;
import com.zzx.backend.assistant.exception.UiViewException;
import com.zzx.backend.assistant.exception.ValidateException;
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

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(FileParseException.class)
    public Boolean FileParseException(FileParseException ex) {
        return Boolean.FALSE;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidateException.class)
    public String ValidateException(ValidateException ex) {
        return ex.getMessage();
    }
}
