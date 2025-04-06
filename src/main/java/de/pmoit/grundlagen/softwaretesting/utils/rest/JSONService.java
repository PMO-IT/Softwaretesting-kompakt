package de.pmoit.grundlagen.softwaretesting.utils.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;


/**
 * Class for handling JSOn Requestresult.
 *
 */

public class JSONService {
    private static final int _TIMEOUT = 400;

    public String getJSONRequestResult(String jsonUrl) throws SocketTimeoutException {
        try {
            URL url = new URL(jsonUrl);
            HttpURLConnection conn = ( HttpURLConnection ) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setReadTimeout(_TIMEOUT);
            conn.setConnectTimeout(_TIMEOUT);
            conn.setRequestProperty("Accept", "application/json");
            if (conn.getResponseCode() != 200) {
                return "Fehler : HTTP error code : " + conn.getResponseCode();
            }
            String output = readJsonStream(conn);
            conn.disconnect();
            return output;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            throw new SocketTimeoutException(e.getMessage());
        }
        return "Fehler, keine Antwort erhalten";
    }

    private String readJsonStream(HttpURLConnection conn) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader( ( conn.getInputStream() )));
        String output = "";
        String readLineResult;
        while ( ( readLineResult = br.readLine() ) != null) {
            output += readLineResult;
        }
        return output;
    }
}
