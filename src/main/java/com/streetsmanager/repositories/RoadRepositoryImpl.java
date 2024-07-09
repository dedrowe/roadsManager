package com.streetsmanager.repositories;

import com.streetsmanager.entity.Road;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoadRepositoryImpl implements RoadRepository {

    @Autowired
    EntityManager em;

    @Override
    public List<Road> getAllRoads() {
        Query query = em.createQuery("from Road", Road.class);

        return query.getResultList();
    }

    @Override
    public Road getRoadById(int id) {
        return em.find(Road.class, id);
    }

    @Override
    public Road saveRoad(Road road) {
        return em.merge(road);
    }

    @Override
    public void deleteRoad(int id) {
        Query query = em.createQuery("delete from Road where id=:id");
        query.setParameter("id", id);
        query.executeUpdate();
    }
}
