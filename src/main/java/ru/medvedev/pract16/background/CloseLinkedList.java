package ru.medvedev.pract16.background;

public class CloseLinkedList<E>{
    private Node head;
    private int size;

    CloseLinkedList() {
        initHead();
    }

    public void initHead() {
        this.head = new Node(null, null, null);
        this.head.next = this.head.prev = this.head;
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public boolean add(MenuItem value) {
        addLast(value);
        return true;
    }

    public void addLast(MenuItem value) {
        if (value != null) {
            Node last = new Node(this.head, this.head.prev, value);
            this.head.prev = this.head.prev.next = last;
            this.size++;
        }
    }

    public boolean remove(MenuItem item) {
        boolean result;
        if (result = item != null && !isEmpty()) {
            Node delete = null;
            for (Node node = this.head.next; node != this.head; node = node.next) {
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

    public boolean remove(String itemName) {
        boolean result;
        if (result = itemName != null && !isEmpty()) {
            Node delete = null;
            for (Node node = this.head.next; node != this.head; node = node.next) {
                if (node.value.name.equals(itemName)) {
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

    public int removeAll(MenuItem item){

        int result = 0;
        if (item != null && !isEmpty()) {
            Node delete;
            for (Node node = this.head.next; node != this.head; node = node.next) {
                if (node.value.equals(item)) {
                    delete = node;
                    delete.prev.next = delete.next;
                    delete.next.prev = delete.prev;
                    this.size--;
                }
            }
        }
        return result;
    }

    public int removeAll(String itemName){

        int result = 0;
        if (itemName != null && !isEmpty()) {
            Node delete;
            for (Node node = this.head.next; node != this.head; node = node.next) {
                if (node.value.name.equals(itemName)) {
                    delete = node;
                    delete.prev.next = delete.next;
                    delete.next.prev = delete.prev;
                    this.size--;
                }
            }
        }
        return result;
    }

    @Override
    public String toString() {
        String s = "";
        for (Node node = this.head.next; node != this.head; node = node.next)
            s+= node.value.toString() + "; ";
        if(s.contains(", "))
            s = s.substring(0, s.length() - 2);
        return s;
    }

    public String[] getNames(){
        String []s = new String[size];
        int i = 0;
        for (Node node = this.head.next; node != this.head; node = node.next) {
            s[i] = node.value.name;
            i++;
        }
        return s;
    }

    public MenuItem[] toArray(){
        MenuItem []items = new MenuItem[size];
        int i = 0;
        for (Node node = this.head.next; node != this.head; node = node.next) {
            items[i] = node.value;
            i++;
        }
        return items;
    }

    public int itemQuantity(MenuItem item){
        int quantity = 0;
        for (Node node = this.head.next; node != this.head; node = node.next) {
            if(node.value.equals(item))
                quantity++;
        }
        return quantity;
    }

    public int itemQuantity(String itemName){
        int quantity = 0;
        for (Node node = this.head.next; node != this.head; node = node.next) {
            if(node.value.name.equals(itemName))
                quantity++;
        }
        return quantity;
    }

    public int getTotalCost(){
        int sum = 0;
        for (Node node = this.head.next; node != this.head; node = node.next)
            sum += node.value.cost;
        return sum;
    }

    public MenuItem[] sortByCost(){

        MenuItem[] mas = this.toArray();

        boolean isSorted = false;
        MenuItem buf;
        while(!isSorted) {
            isSorted = true;
            for (int i = 0; i < mas.length - 1; i++) {
                if (mas[i].cost > mas[i + 1].cost) {
                    isSorted = false;

                    buf = mas[i];
                    mas[i] = mas[i + 1];
                    mas[i + 1] = buf;
                }
            }
        }
        return mas;
    }

    private class Node {
        private Node next;
        private Node prev;
        private MenuItem value;

        Node(Node next, Node prev, MenuItem value) {
            this.next = next;
            this.prev = prev;
            this.value = value;
        }
    }
}
