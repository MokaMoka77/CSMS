package com.csms.modal;

public class OrderLine02 {
    private String id,bill_id,drink_id,price,number;

    public OrderLine02() {
    }

    public OrderLine02(String id, String bill_id, String drink_id, String price, String number) {
        this.id = id;
        this.bill_id = bill_id;
        this.drink_id = drink_id;
        this.price = price;
        this.number = number;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBill_id() {
        return bill_id;
    }

    public void setBill_id(String bill_id) {
        this.bill_id = bill_id;
    }

    public String getDrink_id() {
        return drink_id;
    }

    public void setDrink_id(String drink_id) {
        this.drink_id = drink_id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
