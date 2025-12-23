package com.rakhymzhan.stmps_service.service;

import com.rakhymzhan.stmps_service.model.Crew;
import com.rakhymzhan.stmps_service.model.User;
import com.rakhymzhan.stmps_service.model.dto.CrewCreateRequest;
import com.rakhymzhan.stmps_service.model.dto.CrewUpdateRequest;
import com.rakhymzhan.stmps_service.model.dto.CrewResponse;
import com.rakhymzhan.stmps_service.repo.CrewRepo;
import com.rakhymzhan.stmps_service.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CrewService {

    private final CrewRepo crewRepo;
    private final UserRepo userRepo;

    public CrewResponse createCrew(CrewCreateRequest request) {
        Crew crew = new Crew();
        applyRequestToCrew(crew, request.desd(),
                request.name(), request.leaderId(), request.memberIds());

        return toResponse(crewRepo.save(crew));
    }

    public CrewResponse updateCrew(Long id, CrewUpdateRequest request) {
        Crew crew = crewRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Crew not found, id=" + id));

        applyRequestToCrew(crew, request.desd(),
                request.name(), request.leaderId(), request.memberIds());

        return toResponse(crewRepo.save(crew));
    }

    public CrewResponse getCrew(Long id) {
        Crew crew = crewRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Crew not found, id=" + id));
        return toResponse(crew);
    }

    public List<CrewResponse> getAllCrews(String desd) {
        List<Crew> crews = (desd != null && !desd.isBlank())
                ? crewRepo.findByDesd(desd)
                : crewRepo.findAll();

        return crews.stream().map(this::toResponse).toList();
    }

    public void deleteCrew(Long id) {
        crewRepo.deleteById(id);
    }

    /* ================== helpers ================== */

    private void applyRequestToCrew(
            Crew crew,
            String desd,
            String name,
            Long leaderId,
            List<Long> memberIds
    ) {
        if (desd != null) crew.setDesd(desd);
        if (name != null) crew.setName(name);


        if (leaderId != null) {
            User leader = userRepo.findById(leaderId)
                    .orElseThrow(() -> new IllegalArgumentException("Leader not found: " + leaderId));
            crew.setLeader(leader);
        } else {
            crew.setLeader(null);
        }

        if (memberIds != null) {
            Set<User> members = new HashSet<>(userRepo.findAllById(memberIds));
            crew.setMembers(members);
        }
    }

    private CrewResponse toResponse(Crew crew) {
        Long leaderId = crew.getLeader() != null ? crew.getLeader().getId() : null;
        String leaderName = crew.getLeader() != null ? crew.getLeader().getFullName() : null;

        List<CrewResponse.CrewMemberShort> memberDtos = crew.getMembers().stream()
                .map(u -> new CrewResponse.CrewMemberShort(
                        u.getId(),
                        u.getFullName(),
                        u.getPhoneNumber()
                ))
                .collect(Collectors.toList());

        return new CrewResponse(
                crew.getId(),
                crew.getDesd(),
                crew.getName(),
                leaderId,
                leaderName,
                memberDtos
        );
    }
}