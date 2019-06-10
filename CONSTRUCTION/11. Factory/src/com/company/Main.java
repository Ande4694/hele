package com.company;

public class Main {

    // https://alvinalexander.com/java/java-factory-pattern-example
    public static void main(String[] args) {

        VovseFabrik vovseFabrik = new VovseFabrik();

        Dog lille = vovseFabrik.getDog("lille");
        Dog mellem = vovseFabrik.getDog("mellem");
        Dog stor = vovseFabrik.getDog("stor");

        lille.speak();
        mellem.speak();
        stor.speak();
    }
}
