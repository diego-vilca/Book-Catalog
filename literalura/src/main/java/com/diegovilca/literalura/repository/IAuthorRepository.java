package com.diegovilca.literalura.repository;

import com.diegovilca.literalura.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IAuthorRepository extends JpaRepository<Author, Long> {
    Optional<Author> findByName(String name);
}
