package com.demo.service;

import com.demo.model.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Logger;

@Service
public class FlightsService {

    Flights flights = new Flights();
    ArrayList<Integer> randomCouponNumber = new ArrayList<>();
    public Random rand = new Random();


    public FlightsService(){
        for (int i = 0; i < 100; i++) {
            randomCouponNumber.add(rand.nextInt(1000));
        }
        ArrayList<Flight> flights1 = new ArrayList<>();
        for (int j = 200; j < 300  ; j++) {
            ArrayList<Ticket> tickets = new ArrayList<>();
            for (int i = 0; i < 100; i++) {
                Ticket ticket = new Ticket(i, true, j);
                tickets.add(ticket);
            }
            Flight flight = new Flight(j,tickets);
            flights1.add(flight);
        }
        flights.setFlights(flights1);
    }

    public boolean checkIn(int destId,String baggageId){
        for (int i = 0; i <flights.getFlights().size() ; i++) {
            if(flights.getFlights().get(i).getDestId() == destId){
                Baggage baggage = new Baggage(destId,baggageId);
                flights.getFlights().get(i).baggages.add(baggage);
                return true;
            }
        }
        return false;
    }

    public boolean checkTicketAvailability(int ticketId) {
        for (int i = 0; i < flights.getFlights().size(); i++) {
            for (int j = 0; j < flights.getFlights().get(i).tickets.size(); j++) {
                if (flights.getFlights().get(i).tickets.get(j).getTicketId() == ticketId) {
                    {
                        if (flights.getFlights().get(i).tickets.get(j).isAvailable()) {
                            System.out.println("Ticket with Id = " + ticketId + " is Available");
                            return true;
                        } else {
                            System.out.println("Ticket with Id = " + ticketId + " not Available");
                            return false;
                        }
                    }
                }
            }
        }
        System.out.println("Ticket with Id = " + ticketId + " not Available");
        return false;
    }

    public Long getBaggage(int destId){
        for (int i = 0; i < flights.getFlights().size(); i++) {
            if (flights.getFlights().get(i).getDestId() == destId)
                return flights.getFlights().get(i).baggages.stream().count();
        }
        return null;
    }

    public double isCouponValid(int couponId, Double price) {
        int[] discountArr = {10,50,60};
        Random rand = new Random();
        int x = rand.nextInt(4);
        if(randomCouponNumber.contains(couponId)) {
            double discount = (double) (100 - discountArr[x]) / 100;
            return price * discount;
        }
        return price;
    }
}
