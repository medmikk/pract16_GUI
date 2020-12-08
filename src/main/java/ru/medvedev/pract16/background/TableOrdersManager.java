package ru.medvedev.pract16.background;

import java.io.IOException;
import java.util.Arrays;

public class TableOrdersManager implements OrdersManager{

    private Order[] orders = new Order[0];
    private int size;

    private Table[] tables = {
            new Table(null, 1),
            new Table(null, 2),
            new Table(null, 3),
            new Table(null, 4),
            new Table(null, 5),
            new Table(null, 6),};

    private void checkExisting(int number) throws IOException {
        boolean flg = false;
        for (Table table: tables) {
            if (table.getNumber() == number) {
                flg = true;
                break;
            }
        }
        if(!flg)
            throw (new IOException("IllegalTableNumber"));
    }

    private void checkFree(int index) throws OrderAlreadyAddedException {
        if(!tables[index].isFree())
            throw new OrderAlreadyAddedException();
    }

    private int getIndex(int number){
        for (int i = 0; i < tables.length; i++) {
            if (tables[i].getNumber() == number) {
                return i;
            }
        }
        return 0;
    }

    private int getIndex(Order order){
        for (int i = 0; i < tables.length; i++) {
            if (tables[i].getTableOrder() == order) {
                return i;
            }
        }
        return 0;
    }


    private void resize(int newSize){
        Order[] copyItems = new Order[size];
        if (size > 0 && orders != null) System.arraycopy(orders, 0, copyItems, 0, size);
        orders = new Order[newSize];
        if (size > 0) System.arraycopy(copyItems, 0, orders, 0, size);
        size = newSize;
    }

    private void shift(int index){
        if(index != size - 1)
            if (size - 1 - index >= 0) System.arraycopy(orders, index + 1, orders, index, size - 1 - index);
    }

    public void add(Order order, int tableNumber){
        try {
            checkExisting(tableNumber);
            checkFree(getIndex(tableNumber));
            tables[getIndex(tableNumber)].setOrder(order);
            resize(size + 1);
            orders[size-1] = order;
        } catch (IOException | OrderAlreadyAddedException e) {
            e.printStackTrace();
        }
    }

    public void addItem(MenuItem item, int tableNumber){
        try {
            checkExisting(tableNumber);
            int index = getIndex(tableNumber);
            if(tables[index].getTableOrder()!=null){
                tables[index].getTableOrder().add(item);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //возвращает количество столов
    public int freeTableNumbers(){
        int result = 0;
        for (Table table: tables)
            if (table.isFree())
                result++;
            return result;
    }

    public Table[] getTables(){
        return tables;
    }

    public int[] freeTableNumbersArray(){
        int[] tableNumbers = new int[tables.length];
        int si = 0;
        for (int i = 0; i < tables.length; i++)
            if (tables[i].isFree()) {
                tableNumbers[i] = tables[i].getNumber();
                si++;
            }
        int[] result = new int[si];

        if (si >= 0) System.arraycopy(tableNumbers, 0, result, 0, si);

        return result;
    }

    public Order getOrder(int tableNumber){
        try {
            checkExisting(tableNumber);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tables[getIndex(tableNumber)].getTableOrder();
    }

    public void remove(int tableNumber){
        try {
            checkExisting(tableNumber);
            for (int i = 0; i < size; i++) {
                if (orders[i].equals(tables[getIndex(tableNumber)].getTableOrder())) {
                    shift(i);
                    resize(--size);
                    tables[getIndex(tableNumber)].setOrder(null);
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int remove(Order order){
        int result = 0;
        for (int i = 0; i < size; i++) {
            if (orders[i].equals(tables[getIndex(order)].getTableOrder())) {
                shift(i);
                resize(--size);
                result++;
                tables[getIndex(order)].setOrder(null);
                break;
            }
        }
        return result;
    }

    public int removeAll(Order order){
        int result = 0;
        for (int i = 0; i < size; i++) {
            if (orders[i].equals(tables[getIndex(order)].getTableOrder())) {
                shift(i);
                resize(--size);
                result++;
                tables[getIndex(order)].setOrder(null);
            }
        }
        return result;
    }

    @Override
    public int itemsQuantity(String itemName) {
        int result = 0;
        for(Order order: orders)
            result += order.itemQuantity(itemName);
        return result;
    }

    @Override
    public int itemsQuantity(MenuItem item) {
        int result = 0;
        for(Order order: orders)
            result += order.itemQuantity(item);
        return result;
    }

    @Override
    public Order[] getOrders() {
        return orders;
    }

    @Override
    public int ordersCostSummary() {
        int result = 0;
        for(Order order: orders)
            result += order.costTotal();
        return result;
    }

    @Override
    public int ordersQuantity() {
        return orders.length;
    }

    @Override
    public String toString() {
        String s = "";
        for (Order order: orders)
            s += order;

        return "TableOrdersManager{" +
                "orders=" + Arrays.toString(orders) +
                '}';
    }
}
