package ru.medvedev.pract16.background;

import java.util.Arrays;

public class TableOrder implements Order{

    private Customer customer;
    private MenuItem[] items;
    private int size;

    public TableOrder(){
        size = 0;
        resize(0);
    }

    public TableOrder(MenuItem[] menuItems){
        size = menuItems.length;
        if (menuItems != null) System.arraycopy(menuItems, 0, items, 0, size);
    }

    private void resize(int newSize){
        MenuItem[] copyItems = new MenuItem[size];
        if (size > 0 && items != null) System.arraycopy(items, 0, copyItems, 0, size);
        items = new MenuItem[newSize];
        if (size > 0) System.arraycopy(copyItems, 0, items, 0, size);
        size = newSize;
    }

    private void shift(int index){
        if(index != size - 1)
            if (size - 1 - index >= 0) System.arraycopy(items, index + 1, items, index, size - 1 - index);
    }

    @Override
    public boolean add(MenuItem item) {
        boolean result = false;
        resize(size + 1);
        items[size-1] = item;
        if ( items[size-1].equals(item))
            result = true;
        return result;
    }

    @Override
    public String[] itemsNames() {
        String[] names = new String[size];
        for (int i = 0; i < size; i++)
            names[i] = items[i].name;
        return names;
    }

    @Override
    public int itemsQuantity() {
        return size;
    }

    @Override
    public int itemQuantity(String itemName) {
        int quantity = 0;
        for (int i = 0; i < size; i++) {
            if (items[i].name.equals(itemName))
                quantity++;
        }
        return quantity;
    }

    @Override
    public int itemQuantity(MenuItem item) {
        int quantity = 0;
        for (int i = 0; i < size; i++) {
            if (items[i].equals(item))
                quantity++;
        }
        return quantity;
    }

    @Override
    public MenuItem[] getItems() {
        return items;
    }

    @Override
    public boolean remove(String itemName) {
        boolean result = false;
        for (int i = 0; i < size; i++) {
            if (items[i].name.equals(itemName)) {
                shift(i);
                resize(--size);
                result = true;
                break;
            }
        }
        return result;
    }

    @Override
    public boolean remove(MenuItem item) {
        boolean result = false;
        for (int i = 0; i < size; i++) {
            if (items[i].equals(item)) {
                shift(i);
                resize(--size);
                result = true;
                break;
            }
        }
        return result;
    }

    @Override
    public int removeAll(String itemName) {
        int result = 0;
        for (int i = 0; i < size; i++) {
            if (items[i].name.equals(itemName)) {
                shift(i);
                resize(--size);
                result++;
            }
        }
        return result;
    }

    @Override
    public int removeAll(MenuItem item) {
        int result = 0;
        for (int i = 0; i < size; i++) {
            if (items[i].equals(item)) {
                shift(i);
                resize(--size);
                result++;
            }
        }
        return result;
    }

    @Override
    public MenuItem[] sortItemsByCostDesc() {
        boolean isSorted = false;
        MenuItem buf;
        while(!isSorted) {
            isSorted = true;
            for (int i = 0; i < items.length - 1; i++) {
                if (items[i].cost > items[i + 1].cost) {
                    isSorted = false;

                    buf = items[i];
                    items[i] = items[i + 1];
                    items[i + 1] = buf;
                }
            }
        }
        return items;
    }

    @Override
    public int costTotal() {
        int result = 0;
        for (int i = 0; i < size; i++)
            result += items[i].cost;
        return result;
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
        return customer + " заказ "
                + Arrays.toString(items);
    }
}
