package com.parkshark.dudewheremycar.repository.divisions;

import com.parkshark.dudewheremycar.domain.divisions.Director;
import com.parkshark.dudewheremycar.domain.divisions.Division;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.UUID;

@Repository
public class DirectorRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public DirectorRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Director getDirectorById(UUID id) {
        return entityManager.createQuery("SELECT d FROM Director d WHERE d.id = :id", Director.class)
                .setParameter("id", id)
                .getSingleResult();
    }
}
