package com.demo.service;

import com.demo.model.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Random;

@Service
public class FlightsService {

    Flights flights = new Flights();
    ArrayList<Integer> randomCouponNumber = new ArrayList<>();
    public Random rand = new Random();


    public FlightsService(){
        for (int i = 0; i < 10; i++) {
            randomCouponNumber.add(rand.nextInt(10));
        }
        ArrayList<Flight> flights1 = new ArrayList<>();
        for (int destId = 200; destId < 300  ; destId++) {
            ArrayList<Ticket> tickets = new ArrayList<>();
            for (int ticketId = 0; ticketId < 100; ticketId++) {
                Ticket ticket = new Ticket(ticketId, true, destId);
                tickets.add(ticket);
            }
            Flight flight = new Flight(destId,tickets);
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
                            return true;
                        } else {
                            return false;
                        }
                    }
                }
            }
        }
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
        int x = rand.nextInt(3);
        if(randomCouponNumber.contains(couponId)) {
            double discount = (double) (100 - discountArr[x]) / 100;
            return price * discount;
        }
        return price;
    }
}