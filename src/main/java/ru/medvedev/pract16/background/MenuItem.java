package ru.medvedev.pract16.background;

public class MenuItem {
    protected String name;
    protected String description;
    protected int cost;

    public int getCost() {
        return cost;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "(" + name + ", " +
                description + ", " +
                cost + ')';
    }
}
