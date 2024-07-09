package com.streetsmanager.exception_handling.exceptions;

public class GeoJsonFormatException extends RoadBaseException {
    public GeoJsonFormatException() {
        super("Введенные данные не соответствуют формату GeoJson");
    }
}
