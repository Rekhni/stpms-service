package com.rakhymzhan.stmps_service.service;

import com.rakhymzhan.stmps_service.model.dto.PprCreateRequest;
import com.rakhymzhan.stmps_service.model.dto.PprResponse;
import com.rakhymzhan.stmps_service.model.Crew;
import com.rakhymzhan.stmps_service.model.PprPlan;
import com.rakhymzhan.stmps_service.model.dto.PprUpdateRequest;
import com.rakhymzhan.stmps_service.repo.CrewRepo;
import com.rakhymzhan.stmps_service.repo.PprPlanRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PprPlanService {

    private final PprPlanRepo pprPlanRepo;
    private final CrewRepo crewRepo;

    @Transactional(readOnly = true)
    public List<PprResponse> getAll() {
        return pprPlanRepo.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Transactional
    public PprResponse create(PprCreateRequest req) {
        Crew crew = crewRepo.findById(req.getCrewId())
                .orElseThrow(() -> new IllegalArgumentException("Crew not found: " + req.getCrewId()));

        // (Опционально) простая проверка кварталов — можно расширить
        validateQuarterDate(req.getQ1Date(), 1);
        validateQuarterDate(req.getQ2Date(), 2);
        validateQuarterDate(req.getQ3Date(), 3);
        validateQuarterDate(req.getQ4Date(), 4);

        PprPlan plan = PprPlan.builder()
                .type(req.getType().trim())
                .location(req.getLocation().trim())
                .crew(crew)
                .q1Date(req.getQ1Date())
                .q2Date(req.getQ2Date())
                .q3Date(req.getQ3Date())
                .q4Date(req.getQ4Date())
                .build();

        plan = pprPlanRepo.save(plan);
        return toResponse(plan);
    }

    @Transactional
    public PprResponse updatePpr(Long id, PprUpdateRequest req) {
        PprPlan ppr = pprPlanRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Ppr not found, id=" + id));

        Crew crew = crewRepo.findById(req.getCrewId())
                .orElseThrow(() -> new IllegalArgumentException("Crew not found, id=" + id));

        ppr.setType(req.getType().trim());
        ppr.setLocation(req.getLocation().trim());
        ppr.setCrew(crew);

        validateQuarterDate(req.getQ1Date(), 1);
        validateQuarterDate(req.getQ2Date(), 2);
        validateQuarterDate(req.getQ3Date(), 3);
        validateQuarterDate(req.getQ4Date(), 4);

        ppr.setQ1Date(req.getQ1Date());
        ppr.setQ2Date(req.getQ2Date());
        ppr.setQ3Date(req.getQ3Date());
        ppr.setQ4Date(req.getQ4Date());

        PprPlan saved = pprPlanRepo.save(ppr);
        return toResponse(saved);
    }

    private PprResponse toResponse(PprPlan p) {
        Crew c = p.getCrew();

        // если в Crew есть поле objectsQuantity - верни его.
        // иначе оставь null.
        Integer objectsQty = null;
        try {
            // если у тебя есть getObjectsQuantity()
            // objectsQty = c.getObjectsQuantity();
        } catch (Exception ignored) {}

        String responsible = (c != null) ? c.getLeader().getFullName() : null;

        return PprResponse.builder()
                .id(p.getId())
                .type(p.getType())
                .location(p.getLocation())
                .crewId(c.getId())
                .crewName(c.getName())
                .leaderFullName(c.getLeader().getFullName())
                .responsible(responsible)
                .objectsQuantity(objectsQty)
                .q1Date(p.getQ1Date())
                .q2Date(p.getQ2Date())
                .q3Date(p.getQ3Date())
                .q4Date(p.getQ4Date())
                .build();
    }

    private void validateQuarterDate(java.time.LocalDate d, int q) {
        if (d == null) return;
        int m = d.getMonthValue();
        boolean ok = switch (q) {
            case 1 -> m >= 1 && m <= 3;
            case 2 -> m >= 4 && m <= 6;
            case 3 -> m >= 7 && m <= 9;
            case 4 -> m >= 10 && m <= 12;
            default -> false;
        };
        if (!ok) {
            throw new IllegalArgumentException("Date " + d + " is not in quarter " + q);
        }
    }

    public void deletePpr(Long id) {
        pprPlanRepo.deleteById(id);
    }
}
