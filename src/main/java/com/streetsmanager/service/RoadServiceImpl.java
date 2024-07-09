package com.streetsmanager.service;

import com.streetsmanager.entity.Road;
import com.streetsmanager.entity.RoadDTO;
import com.streetsmanager.exception_handling.exceptions.RoadBaseException;
import com.streetsmanager.mapper.RoadMapper;
import com.streetsmanager.repositories.RoadRepositoryImpl;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoadServiceImpl implements RoadService {

    @Autowired
    private RoadRepositoryImpl roadRepository;

    @Autowired
    private RoadMapper roadMapper;

    @Override
    @Transactional
    public List<RoadDTO> getAllRoads() {

        List<Road> roadList = roadRepository.getAllRoads();

        List<RoadDTO> roadDTOList = new ArrayList<>();

        for (Road road : roadList) {
            roadDTOList.add(roadMapper.createRoadDTO(road));
        }

        return roadDTOList;
    }

    @Override
    @Transactional
    public RoadDTO getRoadById(Integer id) {
        RoadDTO road = roadMapper.createRoadDTO(roadRepository.getRoadById(id));
        if (road == null) {
            throw new RoadBaseException("Дорога с таким id не существует");
        }
        return road;
    }

    @Override
    @Transactional
    public RoadDTO addRoad(Road road) {
        return roadMapper.createRoadDTO(roadRepository.saveRoad(road));
    }

    @Override
    @Transactional
    public RoadDTO updateRoad(Road road) {
        Road oldRoad = roadRepository.getRoadById(road.getId());
        if (road.getName() == null)
            road.setName(oldRoad.getName());
        if (road.getGeometry() == null)
            road.setGeometry(oldRoad.getGeometry());
        return roadMapper.createRoadDTO(roadRepository.saveRoad(road));
    }

    @Override
    @Transactional
    public void deleteRoad(Integer id) {
        roadRepository.deleteRoad(id);
    }
}
