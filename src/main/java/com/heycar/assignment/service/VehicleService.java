package com.heycar.assignment.service;

import com.heycar.assignment.model.VehicleListing;
import com.heycar.assignment.utils.CsvUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.*;
import java.util.stream.Collectors;

/**
 * This service class is responsible for handling of vehicle listing operations and also
 * persisting them in memory storage collection
 */
@Service
public class VehicleService {

    @Autowired
    CsvUtils csvUtils;

    /**
     * Placeholder for data storage of vehicle listings
     */
    Map<String, VehicleListing> vehicleListingMap = new HashMap<>();

    /**
     * This method adds given listings to the collection storage
     * @param vehicleListings - List of Vehicle Listings
     * @param dealerId- The Dealer ID
     */
    public void addVehicleListings(List<VehicleListing> vehicleListings, Integer dealerId){
        for(VehicleListing listing: vehicleListings){

            //Dealer ID and the code combination is chosen to be the unique key
            vehicleListingMap.put(dealerId.toString().concat("::").concat(listing.getCode()), listing);
            listing.setDealerId(dealerId);

        }
    }

    /**
     * This method adds given listings (in CSV format) to the collection storage
     * @param vehicleListings - CSV Input of Vehicle Listings
     * @param dealerId- The Dealer ID
     */
    public void addVehicleFromCSV(String vehicleListings, int dealerId){
        try {

            //Pre-processing of CSV into required data object
            List<VehicleListing> vehicles = csvUtils.read(VehicleListing.class, new ByteArrayInputStream(
                    vehicleListings.replace("power-in-ps", "kW").
                            replaceAll("/", ",").
                            getBytes(Charset.forName("UTF-8"))));
            addVehicleListings(vehicles, dealerId);
        }catch(IOException e){
            System.out.println("Processing Exception while parsing CSV");
        }
    }

    /**
     * This method fetches the information of vehicle listings of a dealer ID (if passed),
     * else returns all
     * @param dealerId - The Dealer ID
     * @return - The vehicle listings
     */
    public List<VehicleListing> getVehicleListings(Integer dealerId){
        if(dealerId!=null){
            return vehicleListingMap.entrySet().stream()
                    .filter(entry->entry.getKey().contains(dealerId.toString().concat("::")))
                    .map(entry->entry.getValue())
                    .collect(Collectors.toList());
        }
        return vehicleListingMap.values().stream().collect(Collectors.toList());
    }

    /**
     * This method searches and returns the vehicle listings based on the input parameters being passed.
     * All are set as optional, and any combination of parameters can be passed
     *
     * @param make
     * @param model
     * @param year
     * @param color
     * @return - The matched vehicle listings based on the search criteria
     */
    public List<VehicleListing> searchVehicleListings(String make, String model, Long year, String color){

       List<VehicleListing> result = new ArrayList<>();

       for(VehicleListing vehicleListing: vehicleListingMap.values().stream().collect(Collectors.toList())) {

           if (make!=null && !make.equals(vehicleListing.getMake())) {
               continue;
           }

           if (model != null && !model.equals(vehicleListing.getModel())) {
               continue;
           }

           if (year != null && year!=vehicleListing.getYear()) {
               continue;
           }

           if (color != null && !color.equals(vehicleListing.getColor())) {
               continue;
           }

           result.add(vehicleListing);
       }
       return result;
    }

}
