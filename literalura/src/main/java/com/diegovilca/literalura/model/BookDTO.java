package com.diegovilca.literalura.model;

import java.util.List;

public record BookDTO(
        String title,
        List<Author> authors,
        String language,
        String downloads
) {
}
