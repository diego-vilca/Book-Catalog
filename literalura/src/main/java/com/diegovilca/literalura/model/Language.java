package com.diegovilca.literalura.model;

public enum Language {
    ENGLISH("en", "english"),
    SPANISH("es", "spanish"),
    PORTUGUESE("pt", "portuguese"),
    FRENCH("fr", "french");

    private String languageCode;
    private String language;

    Language (String languageCode, String language){
        this.languageCode = languageCode;
        this.language = language;
    }

    public String getLanguage() {
        return languageCode;
    }

    public static Language setBookLanguage(String responseLanguage){
        for (Language l : Language.values()){
            if (l.languageCode.equalsIgnoreCase(responseLanguage)){
                return l;
            }
        }
        throw new IllegalArgumentException("No matches found: " + responseLanguage);
    }
}
