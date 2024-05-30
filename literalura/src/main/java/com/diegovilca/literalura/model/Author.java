package com.diegovilca.literalura.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String birth_year;
    private String death_year;
    @OneToMany(mappedBy = "author", fetch = FetchType.EAGER)
    private final List<Book> books;

    public Author() {
        this.books = new ArrayList<>();
    }

    public Author(AuthorDTO authorDTO) {
        this();
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

    public void addBook(Book book) {
        books.add(book);
    }

    @Override
    public String toString() {
        return "Author{" + "name='" + name + '\'' +
                ", birth_year='" + birth_year + '\'' +
                ", death_year='" + death_year + '\'' + '}';
    }
}
