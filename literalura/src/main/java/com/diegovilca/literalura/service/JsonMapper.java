package com.diegovilca.literalura.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonMapper implements IJsonMapper{
    private ObjectMapper objectMapper;

    public JsonMapper(){
        objectMapper = new ObjectMapper();
    }
    
    @Override
    public <T> T fromJson(String json, Class<T> clazz) {
        try {
            return objectMapper.readValue(json, clazz);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
