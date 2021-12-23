package com.parkshark.dudewheremycar.repository.divisions;
import com.parkshark.dudewheremycar.domain.divisions.Division;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DivisionRepository extends JpaRepository<Division, UUID> {
}
