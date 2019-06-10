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

    // path bruges til at fiske html sider frem, til response
    static final String path = System.getProperty("user.dir");

    public static void main(String[] args) {

        try {
            // opretter socket til webserver, således at denne webserver kører på "localhost:1337
            ServerSocket serverSocket = new ServerSocket(1337);

            while(true) {

                System.out.println("Afventer forbindelse");
                //Blokere, indtil der er forbindelse
                Socket socket = serverSocket.accept();
                System.out.println("Forbindelse oprettet");


                serviceTheClient(socket);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void serviceTheClient(Socket socket) {

        try{

            //læser HTTP request fra clienten
            Scanner fraClient = new Scanner(socket.getInputStream());

            String fraClientString = fraClient.nextLine();
            System.out.println(fraClientString);

            StringTokenizer tokenizer = new StringTokenizer(fraClientString);
            // waster første token, som er "GET"
            tokenizer.nextToken();

            //request er /blabla.html
            String request = tokenizer.nextToken();

            //request er nu "blabla"
            System.out.println(request.substring(1, request.length()-5));
            request = request.substring(1, request.length()-5);

            if (request.equals("index")){

                String pathIndex = path + "/index.html";
                File index = new File(pathIndex);

                if (!index.isFile()){
                    System.out.println("index filen kunne ikke findes");
                } else {
                    System.out.println("sending index");
                    sendResponse(index, socket);
                }
            } else if (request.equals("about")){

                String pathAbout = path + "/about.html";
                File about = new File(pathAbout);

                if (!about.isFile()){
                    System.out.println("about filen kunne ikke findes");
                } else {
                    sendResponse(about, socket);
                }
            } else if (request.equals("products")){

                String pathProducts = path + "/products.html";
                File products = new File(pathProducts);

                if (!products.isFile()){
                    System.out.println("products filen kunne ikke findes");
                } else {
                    sendResponse(products, socket);
                }
            }


        } catch (Exception e){
            e.printStackTrace();
        }

    }

    private static void sendResponse(File file, Socket socket){

        try {
            //output stream til at sende response
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());

            System.out.println("fil fundet");
            // parser fra long til int
            int length = (int) file.length();

            // byte array til at holde resourscen, i vores tilfælde html
            byte [] byteArr = new byte[length];

            // læser fra "file" til byteArr, og lukker byteArr
            FileInputStream fileInputStream = new FileInputStream(file);
            fileInputStream.read(byteArr);
            fileInputStream.close();

            // HER ER HTTP HEADER
            dataOutputStream.writeBytes("HTTP:/1.0 200 ITS WORKING\r\n");
            // 200 er http for "ok"
            dataOutputStream.writeBytes("Content-length: "+length+"\r\n");
            dataOutputStream.writeBytes("\r\n");

            // HER ER HTTP "RESOURCE"
            //"byteArr" indeholder index.html i byte format
            dataOutputStream.write(byteArr,0,length);
            dataOutputStream.writeBytes("\n");



        } catch (Exception e) {
            e.printStackTrace();
        }



    }
}

/*
telnet localhost 1337

GET /index.html HTTP/1.1
Host: localhost
 */