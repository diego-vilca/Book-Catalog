package com.diegovilca.literalura.service;

import com.diegovilca.literalura.model.BookDTO;

import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class BookService {
    private final Scanner scanner;
    ApiService apiService;

    public BookService() {
        scanner = new Scanner(System.in);
        apiService = new ApiService();
    }

    public void findBookByTitle(String url) {
        System.out.println("Enter book's title: ");
        String title = scanner.nextLine();

        List<BookDTO> books = apiService.getDataFromApi(url + "?search=" + title);
        List<BookDTO> filteredBooks = filterBooks(books, title);
        System.out.println("\n+---------------------------------------+");
        IntStream.range(0, filteredBooks.size())
                .forEach(i -> {
                    var j = i + 1;
                    System.out.println(j + ". " + filteredBooks.get(i).getTitle() + " (" + filteredBooks.get(i).getLanguages().get(0) + ")");
                });
        System.out.println("+---------------------------------------+\n");
        System.out.println("Enter the number of the book to save:");
        int index = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Book '" + filteredBooks.get(index - 1).getTitle() + "' saved");
        scanner.nextLine();
    }

    private List<BookDTO> filterBooks(List<BookDTO> bookDTOS, String input) {
        List<BookDTO> filterBooks = bookDTOS.stream()
                .filter(bookDTO -> bookDTO.getTitle().toLowerCase().contains(input.toLowerCase()))
                .toList();
        return filterBooks;
    }
}
