package com.demo.api;

import com.demo.model.Cache;
import com.demo.service.FlightsService;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RequestMapping("api/v1/flights")
@RestController
public class FlightsController{

    FlightsService flightsService = new FlightsService();
    private Cache cache =  new Cache();
    private static final Logger LOGGER = Logger.getLogger(FlightsController.class.getName());

    @GetMapping(path = "/checkTicketAvailability")
    public boolean checkTicketAvailability(@RequestParam("ticketId") int ticketId){
        LOGGER.info("New checkTicketAvailability request arrived with params ticketId = " + ticketId );
        String path = "api/v1/flights/checkTicketAvailability/?ticketId=" + ticketId ;
        Boolean resp = (Boolean) cache.getResponseFromCache(path);
        if(resp == null) {
            resp = flightsService.checkTicketAvailability(ticketId);
            cache.addNewRequestToCache(resp,20,path);
            LOGGER.info("CheckTicketAvailability request was answered from DB and new Object was created with expiration = " + 20);
        }
        return resp ;
    }

    @PutMapping(path = "/checkIn")
    public boolean checkIn(@RequestParam("destId")int destId,@RequestParam("baggageId")String baggageId){
        LOGGER.info("New checkIn request arrived with params destId = " + destId +" , baggageId = "  +baggageId);
        boolean resp = flightsService.checkIn(destId,baggageId);
        if(resp)
            LOGGER.info("Baggage was checkedIn successfully");
        else
            LOGGER.info("Baggage was not checkedIn successfully");
        return  resp;
    }

    @PutMapping(path = "/isCouponValid")
    public double isCouponValid(@RequestParam("couponId")int couponId, @RequestParam("price")Double price){
        LOGGER.info("New isCouponValid request arrived with params couponId = " + couponId +" , price = "  +price);
        double returnedPrice = flightsService.isCouponValid(couponId,price);
        if (returnedPrice == price)
            LOGGER.info("The coupon with Id = " + couponId + "is not valid");
        else
            LOGGER.info("Price after discount " +  returnedPrice);
        return returnedPrice ;
    }


    //    @GetMapping(path = "/getBaggage")
//    public Long getBaggage(@RequestParam("destId")int destId){
//        return flightsService.getBaggage(destId);
//    }
}
