package com.company;

public abstract class PizzaDecorator implements Pizza {

    @Override
    public String getDescription() {
        return "Toppings";
    }

    public Pizza pizza;


}
