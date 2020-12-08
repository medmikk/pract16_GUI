package ru.medvedev.pract16.background;

import java.util.HashMap;

public class QueueInternet<E>{
    private Node head;
    private Node tail;
    private int size;
    private HashMap<Order, Address> hashMap = new HashMap<>();

    QueueInternet() {
        initHead();
    }

    public void initHead() {
        this.head = new Node(null, null, null);
        this.tail = this.head;
        this.head.next = this.head.prev = this.head;
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public void chekFreeAddress(Address address) throws OrderAlreadyAddedException {
        if(hashMap.containsValue(address))
            throw new OrderAlreadyAddedException();
        for (Address value : hashMap.values()) {
            if(address.getApartmentNumber() == value.getApartmentNumber() &&
                    address.getBuildingLetter() == value.getBuildingLetter() &&
                    address.getCityName().equals(value.getCityName()) &&
                    address.getStreetName().equals(value.getStreetName()) &&
                    address.getBuildingNumber() == value.getBuildingNumber() &&
                    address.getZipCode() == value.getZipCode())
                throw new OrderAlreadyAddedException();
        }
    }

    public boolean add(Order value, Address address)
    {
        try {
            chekFreeAddress(address);
            if (head.value == null)
                this.head = this.tail = new Node(null, null, value);
            else {
                Node q = new Node(null, null, value);
                q.prev = this.tail;
                this.tail.next = q;
                this.tail = q;
            }
            hashMap.put(value, address);
            this.size++;
            return true;
        } catch (OrderAlreadyAddedException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Order remove() {
        if (isEmpty()) {
            return null;
        }
        this.size--;
        hashMap.remove(this.head.value);
        Node delete = this.head;
        Order oldElement = delete.value;
        this.head = this.head.next;
        if(this.size != 0)
            this.head.prev = null;
        else
            this.tail = null;

        return oldElement;
    }



    public boolean remove(Order item) {
        boolean result;
        if (result = item != null && !isEmpty()) {
            Node delete = null;
            for (Node node = this.head; node != this.tail; node = node.next) {
                if (node.value.equals(item)) {
                    delete = node;
                    break;
                }
            }
            if (result = delete != null) {
                delete.prev.next = delete.next;
                delete.next.prev = delete.prev;
                this.size--;
            }
        }
        return result;
    }

    public int queueItemsQuantity(MenuItem item){
        int quantity = 0;
        for (Node node = this.head; node != this.tail.next; node = node.next) {
                quantity += node.value.itemQuantity(item);
        }
        return quantity;
    }

    public int queueItemsQuantity(String itemName){
        int quantity = 0;
        for (Node node = this.head; node != this.tail.next; node = node.next) {
                quantity += node.value.itemQuantity(itemName);
        }
        return quantity;
    }

    public int queueItemsCost(){
        int sum = 0;
        for (Node node = this.head; node != this.tail.next; node = node.next) {
            sum += node.value.costTotal();
        }
        return sum;
    }

    public Order[] toArray(){
        Order []items = new Order[size];
        int i = 0;
        for (Node node = this.head; node != this.tail.next; node = node.next) {
            items[i] = node.value;
            i++;
        }
        return items;
    }

    @Override
    public String toString() {
        String s = "";
        for (Node node = this.head; node != this.tail.next; node = node.next)
            s+= node.value.toString() + "; ";
        if(s.contains(", "))
            s = s.substring(0, s.length() - 2);
        return s;
    }

    private class Node {
        private Node next;
        private Node prev;
        private Order value;

        Node(Order value){
            this.value = value;
        }

        Node(Node next, Node prev, Order value) {
            this.next = next;
            this.prev = prev;
            this.value = value;
        }
    }
}
