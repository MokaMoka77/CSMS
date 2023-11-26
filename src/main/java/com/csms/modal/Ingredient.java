package com.csms.modal;

import java.sql.Date;

public class Ingredient {
    private String id , name;
    private int weight;
    private Date date;
    private boolean status;

    public Ingredient() {
    }

    public Ingredient(String id, String name, int weight, Date date, boolean status) {
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.date = date;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Ingredient{" + "id=" + id + ", name=" + name + ", weight=" + weight + ", date=" + date + ", status=" + status + '}';
    }
}
