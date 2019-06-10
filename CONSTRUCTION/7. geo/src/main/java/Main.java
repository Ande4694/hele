import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class Main {

    public static void main(String[] args) throws  Exception{

        URL ipInfo = new URL ("https://ipinfo.io/");
        URLConnection yc = ipInfo.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));



        String inputLine;
        String coords;



        while ((inputLine = in.readLine()) != null)

            System.out.println(inputLine);

            if (inputLine.contains("loc")){
                coords = inputLine.substring(8,inputLine.length()-2);
                System.out.println(coords);
            }


        in.close();




        final String GOOGLE_SEARCH_URL = "https://www.google.com/search";

    }
}
