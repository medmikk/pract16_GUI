package ru.medvedev.pract16.background;

public class InternetOrdersManager implements OrdersManager{

    QueueInternet<Order> queueInternet = new QueueInternet<>();

    @Override
    public int itemsQuantity(String itemName) {
        return queueInternet.queueItemsQuantity(itemName);
    }

    @Override
    public int itemsQuantity(MenuItem item) {
        return queueInternet.queueItemsQuantity(item);
    }

    @Override
    public Order[] getOrders() {
        return queueInternet.toArray();
    }

    @Override
    public int ordersCostSummary() {
        return queueInternet.queueItemsCost();
    }

    @Override
    public int ordersQuantity() {
        return queueInternet.size();
    }

    public boolean add(Order order, Address address){
        return queueInternet.add(order, address);
    }

    public Order remove(){
        return  queueInternet.remove();
    }

    @Override
    public String toString() {
        return "InternetOrdersManager ["
                + queueInternet + " ]";
    }
}
