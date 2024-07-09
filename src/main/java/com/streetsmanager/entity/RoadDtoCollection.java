package com.streetsmanager.entity;

import java.util.ArrayList;
import java.util.List;

public class RoadDtoCollection {

    private final String type = "FeatureCollection";

    private List<RoadDTO> features;

    public RoadDtoCollection() {
        features = new ArrayList<RoadDTO>();
    }

    public RoadDtoCollection(List<RoadDTO> features) {
        this.features = features;
    }

    public List<RoadDTO> getFeatures() {
        return features;
    }

    public void setFeatures(List<RoadDTO> features) {
        this.features = features;
    }

    public String getType() {
        return type;
    }
}
