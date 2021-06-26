package com.demo.model;

public class Ticket {
    private int ticketId ;
    private boolean available ;
    private int destId ;

    public Ticket(int ticketId , boolean available,int destId){
        this.ticketId = ticketId ;
        this.available = available ;
        this.destId = destId ;
    }

    public boolean isAvailable() {
        return available;
    }

    public int getTicketId() {
        return ticketId;
    }


}
