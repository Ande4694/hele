package toVejs;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ChatServerTo implements Runnable{
    @Override
    public void run() {

        try {
            ServerSocket serverSocket = new ServerSocket(1010);
            Socket socket = serverSocket.accept(); // blokerer
            System.out.println("Forbundet til Klient");


            //lyt til klient;
            try (Scanner scanner = new Scanner(socket.getInputStream())) {
                while (true) {
                    System.out.println(scanner.nextLine()); // blokerer


                }
            } catch (Exception e) {

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
