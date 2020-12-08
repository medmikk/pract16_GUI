package ru.medvedev.pract16.background;

public final class Dish extends MenuItem {

    public Dish(String name, String description){
        try {
            if (!name.trim().isEmpty() && !description.trim().isEmpty()) {
                this.description = description.trim();
                this.name = name.trim();
            } else throw new IllegalArgumentException();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    public Dish(int cost, String name, String description){
        try {
            if(!name.trim().isEmpty() && !description.trim().isEmpty() && cost > 0) {
                this.description = description.trim();
                this.name = name.trim();
                this.cost = cost;
            }
            else throw new IllegalArgumentException();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }
}
