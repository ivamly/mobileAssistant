package com.zzx.backend.assistant.controller.impl;

import com.zzx.backend.assistant.controller.SenderController;
import com.zzx.backend.assistant.dto.rq.SendOnEmailRq;
import com.zzx.backend.assistant.service.send.SendService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SenderControllerImpl implements SenderController {

    private final SendService sendService;

    @Override
    public Boolean sendOnEmail(SendOnEmailRq rq) {
        return null;
    }
}
