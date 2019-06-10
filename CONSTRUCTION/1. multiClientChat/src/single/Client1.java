package single;

import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client1 {

    public static void main(String[] args)throws Exception {

        Socket socket = new Socket("localhost",1337);
        System.out.println("forbundet");

        Scanner scanner = new Scanner(System.in);
        DataOutputStream das = new DataOutputStream(socket.getOutputStream());

        while (true){
            das.writeBytes(scanner.nextLine()+"\n");
            das.flush();

        }
    }

}
