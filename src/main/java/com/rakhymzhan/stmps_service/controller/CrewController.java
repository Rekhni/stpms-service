package com.rakhymzhan.stmps_service.controller;

import com.rakhymzhan.stmps_service.model.dto.CrewCreateRequest;
import com.rakhymzhan.stmps_service.model.dto.CrewUpdateRequest;
import com.rakhymzhan.stmps_service.model.dto.CrewResponse;
import com.rakhymzhan.stmps_service.service.CrewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/crews")
@RequiredArgsConstructor
@CrossOrigin(origins = {
        "http://localhost:9000",
        "https://my-telecom-app.onrender.com"
}) // порт фронта
public class CrewController {

    private final CrewService crewService;

    @GetMapping
    public List<CrewResponse> getCrews(@RequestParam(required = false) String desd) {
        return crewService.getAllCrews(desd);
    }

    @GetMapping("/{id}")
    public CrewResponse getCrew(@PathVariable Long id) {
        return crewService.getCrew(id);
    }

    @PostMapping
    public CrewResponse createCrew(@RequestBody CrewCreateRequest request) {
        return crewService.createCrew(request);
    }

    @PutMapping("/{id}")
    public CrewResponse updateCrew(@PathVariable Long id,
                                   @RequestBody CrewUpdateRequest request) {
        return crewService.updateCrew(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteCrew(@PathVariable Long id) {
        crewService.deleteCrew(id);
    }
}
