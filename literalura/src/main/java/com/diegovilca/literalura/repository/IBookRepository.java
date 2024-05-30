package com.diegovilca.literalura.repository;

import com.diegovilca.literalura.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBookRepository extends JpaRepository<Book, Long> {
    boolean existsByTitle(String title);
}
