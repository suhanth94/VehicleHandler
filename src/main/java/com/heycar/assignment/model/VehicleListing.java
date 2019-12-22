package com.heycar.assignment.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;

/**
 * This class is the POJO model class to hold information of vehicle listing
 */
@Component
public class VehicleListing {

    @JsonProperty
    private String code;
    @JsonProperty
    private String make;
    @JsonProperty
    private String model;
    @JsonProperty(value="kW")
    private String power;
    @JsonProperty
    private long year;
    @JsonProperty
    private String color;
    @JsonProperty
    private double price;
    @JsonProperty
    private int dealerId;

    public String getCode() {
        return code;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public long getYear() {
        return year;
    }

    public String getColor() {
        return color;
    }

    public void setDealerId(int dealerId) {
        this.dealerId = dealerId;
    }

}
