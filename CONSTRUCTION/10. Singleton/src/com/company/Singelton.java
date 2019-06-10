package com.company;

public class Singelton {

    private static Singelton single_instance = null;

    public String s;

    private Singelton(){
        s = "Hej jeg er en meget unik string i denne Singelton klasse - 0123456789";
    }


    // sync gør at kun en thread har adgang til denne  konstructor
    public static Singelton getInstance(){
        if (single_instance == null) {
            single_instance = new Singelton();
        }

        return single_instance;
    }

}
