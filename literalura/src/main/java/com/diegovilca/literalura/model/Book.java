package com.diegovilca.literalura.model;


import jakarta.persistence.*;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;

    @ManyToOne()
    private Author author;
    @Enumerated(EnumType.STRING)
    private Language language;
    private String downloads;

    public Book() {
    }

    public Book(BookDTO bookDTO) {
        this.title = bookDTO.getTitle();
        this.author = bookDTO.getAuthor();
        this.language = Language.setBookLanguage(bookDTO.getLanguage());
        this.downloads = bookDTO.getDownloads();
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public String getDownloads() {
        return downloads;
    }

    public void setDownloads(String downloads) {
        this.downloads = downloads;
    }

    @Override
    public String toString() {
        return """
                ***************************
                *          Book           *
                ***************************
                Title: %s
                Author: %s
                Language: %s
                Downloads: %s
                """.formatted(title, author.getName(), language.getLanguage(), downloads);
    }
}
