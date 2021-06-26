package com.demo.model;

import java.util.ArrayList;

public class Flight {

    private int destId;
    public ArrayList<Ticket> tickets ;
    public ArrayList<Baggage> baggages = new ArrayList<>();

    public Flight(int destId ,ArrayList<Ticket> tickets){
        this.destId = destId ;
        this.tickets = tickets;
    }
    public int getDestId() {
        return destId;
    }

    public void setDestId(int destId) {
        this.destId = destId;
    }
}
