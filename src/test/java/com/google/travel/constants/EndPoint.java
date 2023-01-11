package com.google.travel.constants;

public enum EndPoint {
    FLIGHTS("/flights");

    public final String endPoint;

    private EndPoint(String endPoint){
        this.endPoint = endPoint;
    }

}
