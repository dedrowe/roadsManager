package com.streetsmanager.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.streetsmanager.entity.Road;
import com.streetsmanager.entity.RoadDTO;
import com.streetsmanager.exception_handling.exceptions.GeoJsonFormatException;
import org.geotools.geojson.geom.GeometryJSON;
import org.locationtech.jts.geom.LineString;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
public class RoadMapper {
    public Road createRoad(RoadDTO roadDTO) {
        try {
            Road road = new Road();
            road.setName(roadDTO.getName());
            road.setId(roadDTO.getId());
            GeometryJSON geometryJSON = new GeometryJSON();
            if (roadDTO.getGeometry() != null)
                road.setGeometry((LineString) geometryJSON.read(roadDTO.getGeometry().toString()));
            return road;
        }
        catch (IOException e) {
            throw new GeoJsonFormatException();
        }
    }

    public RoadDTO createRoadDTO(Road road) {

        try {
            if (road == null)
                return null;
            RoadDTO roadDTO = new RoadDTO();
            roadDTO.setId(road.getId());
            roadDTO.setName(road.getName());
            GeometryJSON geometryJSON = new GeometryJSON();
            String buffer = geometryJSON.toString(road.getGeometry());


            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode a = objectMapper.readTree(buffer);
            roadDTO.setGeometry(a);
            return roadDTO;

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
