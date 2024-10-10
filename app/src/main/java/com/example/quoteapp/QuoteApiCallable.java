package com.example.quoteapp;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.Callable;

import javax.net.ssl.HttpsURLConnection;

// Need this to implement runnable so the methods in this class can be run in a seperate thread to the main UI thread in main activity
public class QuoteApiCallable implements Callable<String> {
    // Attributes
    private String baseURL;
    private String output;

    // Constructor
    public QuoteApiCallable(){
        baseURL = "https://favqs.com/api";
        output = null;
    }

    @Override
    public String call() throws Exception {
        return getQuoteOfTheDay();
    }

    private String getQuoteOfTheDay(){
        String quote;
        String endpointURL = this.baseURL + "/qotd";
        try {
            URL apiurl = new URL(endpointURL);
            HttpsURLConnection connection = (HttpsURLConnection) apiurl.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");
            int responseCode = connection.getResponseCode();
            // 200 means successful connection
            if (responseCode == 200){
                // Somehow parse the information here
                // For now output connection successful
                quote = "SUCCESS, CODE: " + responseCode;
            }
            else{
                // Output connection unsuccessful for now
                quote = "FAIL, CODE: " + responseCode;
            }
            return quote;
        } catch (MalformedURLException e) {
            // can be caused by URL method
            throw new RuntimeException(e);
        } catch (IOException e) {
            // Can be caused by https url open connection
            throw new RuntimeException(e);
        }
    }

    public String getOutput(){
        return this.output;
    }
}
