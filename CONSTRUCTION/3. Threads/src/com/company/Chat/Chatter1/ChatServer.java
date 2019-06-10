package com.company.Chat.Chatter1;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class ChatServer {
    Map<Socket, DataOutputStream> map = new HashMap<>();


    public static void main(String[] args) {
        new ChatServer().runServer();
    }

    // for hvert map entry af socket, skrives der
    private synchronized void broadcasstToClients(String message) {
        for (Map.Entry<Socket, DataOutputStream> entry : map.entrySet()) {
            try {
                entry.getValue().writeBytes(message);
                entry.getValue().flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void runServer() {
        try {
            ServerSocket serverSocket = new ServerSocket(1337);
            while (true) {
                Socket socket = serverSocket.accept(); //blokere
                map.put(socket, new DataOutputStream(socket.getOutputStream()));
                // socket skal lægges i en tråd
                System.out.println("Forbundet til Klient");
                ClientHandler clientHandler = new ClientHandler(socket);
                Thread thread = new Thread(clientHandler);
                thread.start();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    // inner class af chlienthandler
    public class ClientHandler implements Runnable {
        Socket socket;

        public ClientHandler(Socket socket){
            this.socket = socket;
        }
        @Override
        public void run() {
            try (Scanner scanner = new Scanner(socket.getInputStream())) {
                while (true) {
                    String line = scanner.nextLine();
                    System.out.println(line); // blokerer

                    broadcasstToClients(line);
                }
            } catch (Exception e) {

            }
        }
    }
}
