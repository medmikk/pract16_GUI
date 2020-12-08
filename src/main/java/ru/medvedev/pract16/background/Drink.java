package ru.medvedev.pract16.background;

public final class Drink extends MenuItem implements Alcoholable{

    final boolean alcoholable = false;
    final double alVol = 0;
    final DrinkType drinkType = DrinkType.BEER; //просто чтобы было

    public Drink(String name, String description){
        try {
            if (!name.isEmpty() && !description.isEmpty()) {
                this.description = description;
                this.name = name;
            } else throw new IllegalArgumentException();
        } catch (IllegalArgumentException e) {
        e.printStackTrace();
        }
    }

    public Drink(int cost, String name, String description){
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

    @Override
    public boolean isAlcoholicDrink() {
        return alcoholable;
    }

    @Override
    public double getAlcoholVol() {
        return alVol;
    }

    public enum DrinkType{
        BEER,
        WINE,
        VODKA,
        BRANDY,
        RUM,
        MILK,
        JAGERMEISTER,
        SODA
    }
}
