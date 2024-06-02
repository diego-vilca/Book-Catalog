package com.diegovilca.literalura.repository;

import com.diegovilca.literalura.model.Book;
import com.diegovilca.literalura.model.Language;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IBookRepository extends JpaRepository<Book, Long> {
    boolean existsByTitle(String title);

    List<Book> findBookByLanguage(Language language);
}
