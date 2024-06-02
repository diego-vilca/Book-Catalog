package com.diegovilca.literalura.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "authors")
public class Author {
    @OneToMany(mappedBy = "author", fetch = FetchType.EAGER)
    private final List<Book> books;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer birthYear;
    private Integer deathYear;

    public Author() {
        this.books = new ArrayList<>();
    }

    public Author(AuthorDTO authorDTO) {
        this();
        this.name = authorDTO.name();
        this.birthYear = authorDTO.birthYear();
        this.deathYear = authorDTO.deathYear();
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

    public Integer getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(Integer birthYear) {
        this.birthYear = birthYear;
    }

    public Integer getDeathYear() {
        return deathYear;
    }

    public void setDeathYear(Integer deathYear) {
        this.deathYear = deathYear;
    }

    public void addBook(Book book) {
        books.add(book);
    }

    @Override
    public String toString() {
        return """
                ****************************
                *         Author           *
                ****************************
                Name: %s
                Birth Year: %s
                Death Year: %s
                """.formatted(name, birthYear, deathYear);
    }
}
