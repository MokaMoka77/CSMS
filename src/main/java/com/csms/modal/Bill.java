package com.csms.modal;

public class Bill {
    private String id;
    private int totalPrice;
    private String createdAt;
    private Account createdBy;
    private String takeCreateBy;

    public Bill() {
    }

    public Bill(String id, int totalPrice, String createdAt, Account createdBy) {
        this.id = id;
        this.totalPrice = totalPrice;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
    }

    public Bill(String id, int totalPrice, String createdAt, String takeCreateBy) {
        this.id = id;
        this.totalPrice = totalPrice;
        this.createdAt = createdAt;
        this.takeCreateBy = takeCreateBy;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Account getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Account createdBy) {
        this.createdBy = createdBy;
    }

    public String getTakeCreateBy() {
        return takeCreateBy;
    }

    public void setTakeCreateBy(String takeCreateBy) {
        this.takeCreateBy = takeCreateBy;
    }

    @Override
    public String toString() {
        return "Bill{" + "id=" + id + ", totalPrice=" + totalPrice + ", createdAt=" + createdAt + ", createdBy=" + createdBy + '}';
    }
}