package com.streetsmanager.entity;

import jakarta.persistence.*;
import org.locationtech.jts.geom.LineString;

@Entity
@Table(name="roads")
public class Road {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="name")
    private String name;

    @Column(name="coordinates")
    private LineString geometry;

    public Road() {
    }

    public Road(String name, LineString geometry) {
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

    public LineString getGeometry() {
        return geometry;
    }

    public void setGeometry(LineString coordinates) {
        this.geometry = coordinates;
    }

    @Override
    public String toString() {
        return "Street{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coordinates=" + geometry +
                '}';
    }
}
