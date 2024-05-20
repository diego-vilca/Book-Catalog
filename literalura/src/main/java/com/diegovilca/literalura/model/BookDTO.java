package com.diegovilca.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@JsonIgnoreProperties(ignoreUnknown = true)
public final class BookDTO {
    @JsonAlias("title")
    private String title;
    @JsonAlias("authors")
    private List<AuthorDTO> authors;
    @JsonAlias("languages")
    private List<String> languages;
    @JsonAlias("download_count")
    private String downloads;

    public BookDTO() {
    }

    public BookDTO(String title, List<AuthorDTO> authors, List<String> languages, String downloads) {
        this.title = title;
        this.authors = authors;
        this.languages = languages;
        this.downloads = downloads;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<AuthorDTO> getAuthors() {
        return authors;
    }

    public void setAuthors(List<AuthorDTO> authors) {
        this.authors = authors;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public String getDownloads() {
        return downloads;
    }

    public void setDownloads(String downloads) {
        this.downloads = downloads;
    }

    public String getLanguage() {
        Optional<String> opt = languages.stream().findFirst();

        return opt.orElse("not specified");
    }

    public List<Author> getAuthorslist() {
        List<Author> authorsList = this.authors.stream()
                .map(a -> new Author(a))
                .collect(Collectors.toList());

        return authorsList;
    }

    @Override
    public String toString() {
        return "{" + "title='" + title + '\'' +
                ", authors=" + authors +
                ", languages=" + languages +
                ", downloads='" + downloads + '\'' + '}';
    }
}
