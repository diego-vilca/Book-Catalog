package com.diegovilca.literalura.model;

import java.time.LocalDate;

public class Author {
    private Long id;
    private String name;
    private String birth_year;
    private String death_year;

    public Author(AuthorDTO authorDTO){
        this.name = authorDTO.name();
        this.birth_year = authorDTO.birth_year();
        this.death_year = authorDTO.death_year();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirth_year() {
        return birth_year;
    }

    public void setBirth_year(String birth_year) {
        this.birth_year = birth_year;
    }

    public String getDeath_year() {
        return death_year;
    }

    public void setDeath_year(String death_year) {
        this.death_year = death_year;
    }

    @Override
    public String toString() {
        return "Author{" +
                "name='" + name + '\'' +
                ", birth_year='" + birth_year + '\'' +
                ", death_year='" + death_year + '\'' +
                '}';
    }
}
