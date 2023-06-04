package com.example.eatswunee_bistro;

public class menuList {
    String order_num;
    String bistro_num;
    String menu_name;
    String date;
    String menu_num;

    public menuList(String order_num, String bistro_num, String menu_name, String date, String menu_num) {
        this.order_num = order_num;
        this.bistro_num = bistro_num;
        this.menu_name = menu_name;
        this.date = date;
        this.menu_num = menu_num;
    }

    public String getOrder_num() {return order_num;}
    public String getBistro_num() {return bistro_num;}
    public String getMenu_name() {return menu_name;}
    public String getDate() {return date;}
    public String getMenu_num() {return menu_num;}

    public void setOrder_num(String order_num) {this.order_num = order_num;}
    public void setBistro_num(String bistro_num) {this.bistro_num = bistro_num;}
    public void setMenu_name(String menu_name) {this.menu_name = menu_name;}
    public void setDate(String date) {this.date = date;}
    public void setMenu_num(String menu_num) {this.menu_num = menu_num;}
}
