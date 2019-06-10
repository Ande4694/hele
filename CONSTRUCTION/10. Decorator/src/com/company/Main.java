package com.company;

public class Main {
    //https://www.javacodegeeks.com/2015/09/decorator-design-pattern.html

    public static void addCheese(Pizza pizza){
        pizza = new Cheese(pizza);
    }

    public static void addTomatoSauce(Pizza pizza){
        pizza = new TomatoSauce(pizza);
    }

    public static void addPepperoni(Pizza pizza){
        pizza = new Pepperoni(pizza);
    }


    public static void main(String[] args) {

        Pizza pizza = new SimplePizza();
        
        System.out.println(pizza.getDescription());
        System.out.println(pizza.getPrice());
        System.out.println(pizza);


        pizza = new TomatoSauce(pizza);
        pizza = new Cheese(pizza);
        pizza = new Pepperoni(pizza);


        System.out.println(pizza.getDescription());
        System.out.println(pizza.getPrice());
        System.out.println(pizza);

        Pizza pizza2 = new SimplePizza();
        addTomatoSauce(pizza2);
        addCheese(pizza2);
        addPepperoni(pizza2);



    }
}
