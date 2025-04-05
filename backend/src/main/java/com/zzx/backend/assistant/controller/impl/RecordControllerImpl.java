package com.zzx.backend.assistant.controller.impl;

import com.zzx.backend.assistant.controller.RecordController;
import com.zzx.backend.assistant.dto.rs.RecordWithSummaryRs;
import com.zzx.backend.assistant.service.view.impl.UiViewServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequiredArgsConstructor
public class RecordControllerImpl implements RecordController {

    private final UiViewServiceImpl uiViewServiceImpl;

    @Override
    public Collection<RecordWithSummaryRs> getAllForUi() {
        return uiViewServiceImpl.getRecordWithSummary();
    }
}
