package com.streetsmanager.entity;

import com.fasterxml.jackson.databind.JsonNode;

public class RoadDTO {

    private Integer id;

    private String name;

    private JsonNode geometry;

    public RoadDTO() {
    }

    public RoadDTO(String name, JsonNode geometry) {
        this.name = name;
        this.geometry = geometry;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public JsonNode getGeometry() {
        return geometry;
    }

    public void setGeometry(JsonNode geometry) {
        this.geometry = geometry;
    }
}
