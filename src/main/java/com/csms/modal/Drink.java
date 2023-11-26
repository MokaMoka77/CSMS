package com.csms.modal;

public class Drink {
    private String id ;
    private Category ca;
    private String name , pathImage;
    private int price;
    private boolean status;

    public Drink() {
    }

    public Drink(String id, Category ca, String name, String pathImage, int price, boolean status) {
        this.id = id;
        this.ca = ca;
        this.name = name;
        this.pathImage = pathImage;
        this.price = price;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Category getCa() {
        return ca;
    }

    public void setCa(Category ca) {
        this.ca = ca;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPathImage() {
        return pathImage;
    }

    public void setPathImage(String pathImage) {
        this.pathImage = pathImage;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }


    @Override
    public String toString() {
        return "{id:'"+id+"', cateId:'"+ca.getId()+"', name:'"+name+"', pathImage:'"+pathImage+"', price:'"+price+"',status:'"+status+"'}";
    }
}
