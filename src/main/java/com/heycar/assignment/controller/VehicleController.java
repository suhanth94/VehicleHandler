package com.heycar.assignment.controller;

import com.heycar.assignment.model.VehicleListing;
import com.heycar.assignment.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * This controller class is the responsible for handling REST API requests for
 * creating, updating and searching vehicle listings
 */

@RestController
public class VehicleController {

    @Autowired
    VehicleService vehicleService;


    @RequestMapping(value = "/vehicle-listings/{dealer_id}", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity addVehicleListings(@PathVariable("dealer_id") int dealerId,
                                             @RequestBody List<VehicleListing> vehicleListings){
        vehicleService.addVehicleListings(vehicleListings, dealerId);
        return ResponseEntity.status(200).body("Listings Added Successfully");
    }

    @RequestMapping(value = "/upload_csv/{dealer_id}", method = RequestMethod.POST, consumes = "text/csv")
    public ResponseEntity addVehicleListingsFromCSV(@PathVariable("dealer_id") int dealerId,
                                                    @RequestBody String vehicleListings) {
        vehicleService.addVehicleFromCSV(vehicleListings, dealerId);
        return ResponseEntity.status(200).body("Listings Added Successfully");
    }

    @RequestMapping(value = "/vehicle-listings",method = RequestMethod.GET)
    public List<VehicleListing> getVehicleListings(@RequestParam(name="dealer_id", required = false)
                                                               Integer dealerId){
        return vehicleService.getVehicleListings(dealerId);
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public List<VehicleListing> searchVehicleListings(@RequestParam (required = false) String make,
                                                      @RequestParam (required = false) String model,
                                                      @RequestParam (required =  false) Long year,
                                                      @RequestParam (required = false) String color){

        return vehicleService.searchVehicleListings(make, model, year, color);

    }






}
