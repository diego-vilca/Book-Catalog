package com.diegovilca.literalura.model;

public record AuthorDTO(
        String name,
        String birth_year,
        String death_year
) {
    @Override
    public String toString() {
        return "{" +
                "name='" + name + '\'' +
                ", birth_year='" + birth_year + '\'' +
                ", death_year='" + death_year + '\'' +
                '}';
    }
}
