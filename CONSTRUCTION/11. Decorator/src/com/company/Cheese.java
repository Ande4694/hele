package com.company;

public class Cheese extends PizzaDecorator {

    private final Pizza pizza;

    public Cheese(Pizza pizza){
        this.pizza = pizza;
    }


    @Override
    public String getDescription() {
        return pizza.getDescription()+", Cheese";
    }

    @Override
    public int getPrice() {
        return pizza.getPrice() + 10;
    }
}
