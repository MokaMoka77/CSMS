package com.csms.modal;

public class OrderLine {
    private Bill bill;
    private Drink drink;
    private int quantity;
    private int totalPrice;

    public OrderLine() {
    }

    public OrderLine(Bill bill, Drink drink, int quantity, int totalPrice) {
        this.bill = bill;
        this.drink = drink;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

    public Drink getDrink() {
        return drink;
    }

    public void setDrink(Drink drink) {
        this.drink = drink;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "OrderLine{" + ", bill=" + bill + ", drink=" + drink + ", quantity=" + quantity + ", totalPrice=" + totalPrice + '}';
    }
}
