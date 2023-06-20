package com.example.eatswunee_bistro.api;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
//import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class orders {

    @SerializedName("order_id")
    @Expose
    private String orderId;
    @SerializedName("order_num")
    @Expose
    private String orderNum;
    @SerializedName("menus")
    @Expose
    private List<menus> menus;
    @SerializedName("menu_name")
    @Expose
    private String menuName;
    @SerializedName("menu_cnt")
    @Expose
    private String menuCnt;
    @SerializedName("order_created_at")
    @Expose
    private String orderCreatedAt;

    public orders(){return;}
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuCnt() {
        return menuCnt;
    }

    public void setMenuCnt(String menuCnt) {
        this.menuCnt = menuCnt;
    }

    public String getOrderCreatedAt() {
        return orderCreatedAt;
    }

    public void setOrderCreatedAt(String orderCreatedAt) {
        this.orderCreatedAt = orderCreatedAt;
    }

    public List<menus> getmenusList() {
        return menus;
    }

    public void setmenusList(List<menus> menus) {
        this.menus = menus;
    }

}