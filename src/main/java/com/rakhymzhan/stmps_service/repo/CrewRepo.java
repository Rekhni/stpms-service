package com.rakhymzhan.stmps_service.repo;


import com.rakhymzhan.stmps_service.model.Crew;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CrewRepo extends JpaRepository<Crew, Long> {
    List<Crew> findByDesd(String desd);

    // Можно будет добавить ещё фильтры, например activeFrom/activeTo
}