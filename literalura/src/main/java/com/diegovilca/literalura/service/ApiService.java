package com.diegovilca.literalura.service;

import com.diegovilca.literalura.model.BookDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class ApiService {
    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

    public ApiService() {
        this.httpClient = HttpClient.newHttpClient();
        this.objectMapper = new ObjectMapper();
    }

    public List<BookDTO> getDataFromApi(String url) {
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(url))
//                .timeout(Duration.ofSeconds(60))
                .header("Content-Type", "application/json")
                .GET()
                .build();

        HttpResponse<String> httpResponse = null;
        try {
            httpResponse = this.httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        if (httpResponse.statusCode() == 200) {
            String json = httpResponse.body();

            return jsonToListOfBookDTO(json);

        } else {
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

    private List<BookDTO> jsonToListOfBookDTO(String json) {
        try {
            JsonNode rootNode = objectMapper.readTree(json);
            JsonNode booksNode = rootNode.path("results");

            return objectMapper.convertValue(booksNode, new TypeReference<List<BookDTO>>() {
            });

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
