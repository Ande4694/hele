package toVejs;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient implements Runnable {


    @Override
    public void run() {


        try {
            Socket socket = new Socket("localhost", 1010);
            System.out.println("Er forbundet til server");


            // Send til server:
            Scanner scanner = new Scanner(System.in);

            DataOutputStream dOut = new DataOutputStream(socket.getOutputStream());

            while (true) {
                dOut.writeBytes(scanner.nextLine() + "\n");
                dOut.flush();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}