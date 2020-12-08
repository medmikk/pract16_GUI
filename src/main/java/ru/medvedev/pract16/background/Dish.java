package ru.medvedev.pract16.background;

public final class Dish extends MenuItem {

    public Dish(String name, String description){
        try {
            if (!name.isEmpty() && !description.isEmpty()) {
                this.description = description;
                this.name = name;
            } else throw new IllegalArgumentException();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    public Dish(int cost, String name, String description){
        try {
            if(!name.isEmpty() && !description.isEmpty() && cost > 0) {
                this.description = description;
                this.name = name;
                this.cost = cost;
            }
            else throw new IllegalArgumentException();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }
}
