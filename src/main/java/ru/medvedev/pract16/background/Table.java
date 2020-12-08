package ru.medvedev.pract16.background;

public class Table {
    private Order tableOrder;
    private int number;

    public int getNumber() {
        return number;
    }

    public boolean isFree() {
        return tableOrder == null;
    }

    public void setOrder(Order order){
        this.tableOrder = order;
    }

    public Order getTableOrder() {
        return tableOrder;
    }

    public void setNumber(int number){
        this.number = number;
    }

    Table(Order order, int number){
        this.tableOrder = order;
        this.number = number;
    }
}
