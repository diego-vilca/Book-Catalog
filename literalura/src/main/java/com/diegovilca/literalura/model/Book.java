package com.diegovilca.literalura.model;


import java.util.List;

public class Book {
    private Long id;
    private String title;
    private List<Author> authors;
    private String language;
    private String downloads;

    public Book(BookDTO bookDTO) {
        this.title = bookDTO.getTitle();
        this.authors = bookDTO.getAuthorslist();
        this.language = bookDTO.getLanguage();
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

    public List<Author> getAuthors() {
        return authors;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
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
        return "Book{" +
                "title='" + title + '\'' +
                ", authors=" + authors +
                ", language='" + language + '\'' +
                ", downloads='" + downloads + '\'' +
                '}';
    }
}
