package com.parkshark.dudewheremycar.repository.divisions;

import com.parkshark.dudewheremycar.domain.divisions.Director;
import com.parkshark.dudewheremycar.domain.divisions.Division;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
public class DivisionRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public DivisionRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public void addDivision(Division division) {
        entityManager.persist(division);
    }

}
