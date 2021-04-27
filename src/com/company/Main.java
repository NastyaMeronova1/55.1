package com.company;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Main {

    public static void main(String[] args) throws Exception {
        Main main = new Main();
        main.sendGet();

        Document doc = Jsoup.connect("https://www.nbkr.kg")
                .timeout(6000).get();
        Elements body = doc.select("h3.name");
        Elements bodyTwo = doc.select("h4");
        System.out.println(body);
        System.out.println(bodyTwo);

    }
   private void sendGet() throws Exception {

        String url = "https://www.nbkr.kg";

        HttpURLConnection httpClient =
                (HttpURLConnection) new URL(url).openConnection();

        // optional default is GET
        httpClient.setRequestMethod("GET");

        //add request header
        httpClient.setRequestProperty("User-Agent", "Mozilla/5.0");

        int responseCode = httpClient.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(httpClient.getInputStream()))) {

            StringBuilder response = new StringBuilder();
            String line;

            while ((line = in.readLine()) != null) {
                response.append(line);
            }
            //print result
            System.out.println(response.toString());
     }

    }

}
