package ru.medvedev.pract16.background;

public interface Order{
    boolean add(MenuItem item);
    String[] itemsNames();
    int itemsQuantity();
    int itemQuantity(String itemName);
    int itemQuantity(MenuItem item);
    MenuItem[] getItems();
    boolean remove(String itemName);
    boolean remove(MenuItem item);
    int removeAll(String itemName);
    int removeAll(MenuItem item);
    MenuItem[] sortItemsByCostDesc();
    int costTotal();
    Customer getCustomer();
    void setCustomer(Customer customer);

}
