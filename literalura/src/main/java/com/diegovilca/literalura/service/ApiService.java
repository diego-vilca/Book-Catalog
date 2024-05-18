package com.diegovilca.literalura.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class ApiService {
    private final HttpClient httpClient;

    public ApiService(){
        this.httpClient = HttpClient.newHttpClient();
    }

    public String getDataFromApi(String url) throws IOException, InterruptedException {
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .timeout(Duration.ofSeconds(30))
                .header("Content-Type", "application/json")
                .GET()
                .build();

        HttpResponse<String> httpResponse = this.httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        if (httpResponse.statusCode() == 200){
            return httpResponse.body();
        }else {
            switch (httpResponse.statusCode()) {
                case 400:
                    throw new RuntimeException("Bad Request: " + httpResponse.body());
                case 401:
                    throw new RuntimeException("Unauthorized: " + httpResponse.body());
                case 404:
                    throw new RuntimeException("Not Found: " + httpResponse.body());
                default:
                    throw new RuntimeException("Error: " + httpResponse.statusCode() + " " + httpResponse.body());
            }
        }
    }

}
