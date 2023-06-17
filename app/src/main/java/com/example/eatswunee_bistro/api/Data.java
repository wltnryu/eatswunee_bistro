package com.example.eatswunee_bistro.api;

import java.util.List;
//import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Data {

    @SerializedName("orders")
    @Expose
    private List<orders> ordersList;

    @SerializedName("restaurant_name")
    @Expose
    private String restaurant_name;

    public List<orders> getOrdersList() {
        return ordersList;
    }

    public String getRestaurant_name() {
        return restaurant_name;
    }

    public void setOrdersList(List<orders> ordersList) {
        this.ordersList = ordersList;
    }
}