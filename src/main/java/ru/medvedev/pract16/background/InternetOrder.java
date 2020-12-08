package ru.medvedev.pract16.background;

public class InternetOrder implements Order{

    private Customer customer;
    private CloseLinkedList<MenuItem> menuItemList = new CloseLinkedList<>();

    public InternetOrder(){
        menuItemList.initHead();
    }

    public InternetOrder(MenuItem[] items){
        menuItemList.initHead();
        for (MenuItem item : items)
            menuItemList.add(item);
    }

    @Override
    public boolean add(MenuItem item) {
        return menuItemList.add(item);
    }

    @Override
    public String[] itemsNames() {
        return menuItemList.getNames();
    }

    @Override
    public int itemsQuantity() {
        return menuItemList.size();
    }

    @Override
    public int itemQuantity(String itemName) {
        return menuItemList.itemQuantity(itemName);
    }

    @Override
    public int itemQuantity(MenuItem item) {
        return menuItemList.itemQuantity(item);
    }

    @Override
    public MenuItem[] getItems() {
        return menuItemList.toArray();
    }

    @Override
    public boolean remove(String itemName) {
        return menuItemList.remove(itemName);
    }

    @Override
    public boolean remove(MenuItem item) {
        return menuItemList.remove(item);
    }

    @Override
    public int removeAll(String itemName) {
        return menuItemList.removeAll(itemName);
    }

    @Override
    public int removeAll(MenuItem item) {
        return menuItemList.removeAll(item);
    }

    @Override
    public MenuItem[] sortItemsByCostDesc() {
        return menuItemList.sortByCost();
    }

    @Override
    public int costTotal() {
        return menuItemList.getTotalCost();
    }

    @Override
    public Customer getCustomer() {
        return customer;
    }

    @Override
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return customer + " заказ [ " +
                menuItemList +
                " ]";
    }
}
