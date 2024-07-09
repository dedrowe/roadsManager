package com.streetsmanager.service;

import com.streetsmanager.entity.Road;
import com.streetsmanager.entity.RoadDTO;

import java.util.List;

public interface RoadService {

    List<RoadDTO> getAllRoads();

    RoadDTO getRoadById(Integer id);

    RoadDTO addRoad(Road road);

    RoadDTO updateRoad(Road road);

    void deleteRoad(Integer id);
}
