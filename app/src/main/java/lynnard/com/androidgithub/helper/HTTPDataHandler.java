package lynnard.com.androidgithub.helper;


import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.net.HttpURLConnection;
import java.net.URL;


/**
 * Created by Lynnard on 7/23/2016.
 */
public class HTTPDataHandler {

    static String stream = null;

    public HTTPDataHandler(){
    }

    public String GetHTTPData(String urlString){
        try{
            URL url = new URL(urlString);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            // Check the connection status
            if(urlConnection.getResponseCode() == 200)
            {
                // if response code = 200 ok
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());

                // Read the BufferedInputStream
                BufferedReader r = new BufferedReader(new InputStreamReader(in));
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = r.readLine()) != null) {
                    sb.append(line);
                }

                // End reading
                stream = sb.toString();


                // Disconnect the HttpURLConnection
                urlConnection.disconnect();
            }
            else
            {
                return null;
            }
        }catch (Exception e){
            e.printStackTrace();
        } finally {

        }
        // Return the data from specified url
        return stream;
    }
}
