package com.rakhymzhan.stmps_service.controller;

import com.rakhymzhan.stmps_service.model.dto.*;
import jakarta.validation.Valid;
import com.rakhymzhan.stmps_service.service.PprPlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pprs")
@RequiredArgsConstructor
@CrossOrigin(origins = {
        "http://localhost:9000",
        "https://my-telecom-app.onrender.com"
}) // лучше настроить нормально, но для dev ок
public class PprPlanController {

    private final PprPlanService pprPlanService;

    @GetMapping
    public List<PprResponse> getAll() {
        return pprPlanService.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PprResponse create(@Valid @RequestBody PprCreateRequest request) {
        return pprPlanService.create(request);
    }

    @PutMapping("/{id}")
    public PprResponse updatePpr(
            @PathVariable Long id,
            @RequestBody PprUpdateRequest request
    ) {
        return pprPlanService.updatePpr(id, request);
    }

    @DeleteMapping("/{id}")
    public void deletePpr(@PathVariable Long id) {
        pprPlanService.deletePpr(id);
    }
}
