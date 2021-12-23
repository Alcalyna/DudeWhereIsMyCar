package com.parkshark.dudewheremycar.repository.divisions;

import com.parkshark.dudewheremycar.domain.divisions.DivisionSubdivision;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Repository
public interface DivisionSubdivisionRepository extends JpaRepository<DivisionSubdivision, UUID> {
    List<DivisionSubdivision> findByIdParent(UUID idParent);

    @Transactional
    void removeDivisionSubdivisionByIdParent(UUID idParent);
}
