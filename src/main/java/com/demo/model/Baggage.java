package com.demo.model;

public class Baggage {

    private String baggageId ;
    private int destId ;


    public Baggage(int destId, String baggageId) {
        this.baggageId = baggageId ;
        this.destId = destId ;
    }
}
