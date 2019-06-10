package com.company;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {



    public static void main(String[] args) {

        try {
            // opretter socket til webserver, således at denne webserver kører på "localhost:1337
            ServerSocket serverSocket = new ServerSocket(1337);

            while(true) {

                System.out.println("Afventer forbindelse");
                //Blokere, indtil der er forbindelse
                Socket socket = serverSocket.accept();
                System.out.println("Forbindelse oprettet");


                Handler handler = new Handler(socket);
                Thread thread = new Thread(handler);
                thread.start();
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

/*
telnet localhost 1337

GET /index.html HTTP/1.1
Host: localhost
 */