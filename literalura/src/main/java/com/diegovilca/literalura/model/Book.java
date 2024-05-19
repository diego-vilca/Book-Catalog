package com.diegovilca.literalura.model;


import java.util.List;

public class Book {
    private Long id;
    private String title;
    private List<Author> authors;
    private String language;
    private String downloads;

    public Book(BookDTO bookDTO){
        this.title = bookDTO.title();
        this.authors = bookDTO.authors();
        this.language = bookDTO.language();
        this.downloads = bookDTO.downloads();
    }

    public Long getId(){
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
                "downloads='" + downloads + '\'' +
                ", language='" + language + '\'' +
                ", authors=" + authors +
                ", title='" + title + '\'' +
                ", downloads='" + downloads + '\'' +
                '}';
    }
}
