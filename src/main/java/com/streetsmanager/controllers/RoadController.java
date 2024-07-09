package com.streetsmanager.controllers;

import com.streetsmanager.entity.RoadDTO;
import com.streetsmanager.entity.RoadDtoCollection;
import com.streetsmanager.exception_handling.exceptions.GeoJsonFormatException;
import com.streetsmanager.mapper.RoadMapper;
import com.streetsmanager.service.RoadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/roads")
public class RoadController {

    private final RoadService roadService;

    private final RoadMapper roadMapper;

    @Autowired
    public RoadController(RoadService roadService, RoadMapper roadMapper) {
        this.roadService = roadService;
        this.roadMapper = roadMapper;
    }

    @GetMapping
    public RoadDtoCollection getAllRoads() {
        return new RoadDtoCollection(roadService.getAllRoads());
    }

    @GetMapping("/{id}")
    public RoadDTO getRoadById(@PathVariable("id") Integer id) {
        return roadService.getRoadById(id);
    }

    @PostMapping
    public RoadDTO addRoad(@RequestBody RoadDTO road) {
        if (road.getGeometry() == null || road.getName() == null)
            throw new GeoJsonFormatException();
        return roadService.addRoad(roadMapper.createRoad(road));
    }

    @PutMapping("/{id}")
    public RoadDTO updateRoad(@RequestBody RoadDTO road, @PathVariable("id") Integer id) {
        road.setId(id);
        return roadService.updateRoad(roadMapper.createRoad(road));
    }

    @DeleteMapping("/{id}")
    public String deleteRoad(@PathVariable("id") Integer id) {
        roadService.deleteRoad(id);
        return "Дорога с id = " + id + " удалена";
    }
}
