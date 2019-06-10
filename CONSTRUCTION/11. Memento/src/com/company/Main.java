package com.company;

public class Main {

    public static void main(String[] args) {

        Originator originator = new Originator();
        Caretaker caretaker = new Caretaker();

        originator.setState("state 1");
        originator.setState("state 2");
        caretaker.add(originator.saveToStateToMemento());

        originator.setState("state 3");
        caretaker.add(originator.saveToStateToMemento());

        originator.setState("state 4");


        System.out.println("current state : " + originator.getState());

        originator.getStateFromMemento(caretaker.get(0));
        System.out.println("current state : " + originator.getState());

        originator.getStateFromMemento(caretaker.get(1));
        System.out.println("current state : " + originator.getState());

    }
}
