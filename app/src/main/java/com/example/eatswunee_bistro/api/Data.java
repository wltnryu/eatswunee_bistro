package com.example.eatswunee_bistro.api;

import com.example.eatswunee_bistro.api.Notification;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Data {

    @SerializedName("orders")
    @Expose
    private List<orders> ordersList;

    @SerializedName("restaurant_name")
    @Expose
    private String restaurant_name;

    @SerializedName("notifications")
    @Expose
    private List<Notification> notifications;

    @SerializedName("today_total_revenue")
    @Expose
    private String today_total_revenue;

    @SerializedName("total_revenue")
    @Expose
    private String total_revenue;

    @SerializedName("order_num")
    @Expose
    private String order_num;

    @SerializedName("order_id")
    @Expose
    private String order_id;

    @SerializedName("menus")
    @Expose
    private List<menus> menusList;

    public List<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }


    public List<orders> getOrdersList() {
        return ordersList;
    }

    public String getRestaurant_name() {
        return restaurant_name;
    }

    public void setOrdersList(List<orders> ordersList) {
        this.ordersList = ordersList;
    }

    public String getToday_total_revenue() {
        return today_total_revenue;
    }

    public String getTotal_revenue() {
        return total_revenue;
    }

    public String getOrder_num() {
        return order_num;
    }

    public String getOrder_id() {
        return order_id;
    }

    public List<menus> getMenusList(){return menusList;}
}