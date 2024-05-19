package com.diegovilca.literalura.model;


public class Book {
    private Long id;
    private String title;
    private String language;
    private String downloads;

    public Book(BookDTO bookDTO){
        this.title = bookDTO.title();
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
                "language='" + language + '\'' +
                ", downloads='" + downloads + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
