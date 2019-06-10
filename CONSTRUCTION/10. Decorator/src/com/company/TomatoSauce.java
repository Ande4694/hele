package com.company;

public class TomatoSauce extends PizzaDecorator {

    private final Pizza pizza;

    public TomatoSauce(Pizza pizza){
        this.pizza = pizza;
    }


    @Override
    public String getDescription() {
        return pizza.getDescription()+", TomatoSauce";
    }

    @Override
    public int getPrice() {
        return pizza.getPrice() + 5;
    }
}