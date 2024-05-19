package com.diegovilca.literalura.service;

public interface IJsonMapper {
    <T> T fromJson(String json, Class<T> clazz);
}
