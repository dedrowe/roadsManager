package com.streetsmanager.repositories;

import com.streetsmanager.entity.Road;

import java.util.List;

public interface RoadRepository {
    List<Road> getAllRoads();

    Road getRoadById(int id);

    Road saveRoad(Road road);

    void deleteRoad(int id);
}
