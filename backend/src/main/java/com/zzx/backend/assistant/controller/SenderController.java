package com.zzx.backend.assistant.controller;

import com.zzx.backend.assistant.dto.rq.SendOnEmailRq;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/v1/sendOnEmail")
public interface SenderController {

    @PostMapping
    Boolean sendOnEmail(SendOnEmailRq rq);
}
