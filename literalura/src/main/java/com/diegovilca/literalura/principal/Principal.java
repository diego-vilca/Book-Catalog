package com.diegovilca.literalura.principal;

import com.diegovilca.literalura.service.BookService;

import java.util.Scanner;

public class Principal {
    private final String URL_BASE = "https://gutendex.com/books/";
    private final Scanner scanner = new Scanner(System.in);
    private final BookService bookService;

    public Principal() {
        bookService = new BookService();
    }

    public void showMenu() {
        var option = -1;
        while (option != 0) {
            String menu = """
                    
                    +---------------------------------------+
                    |                Main Menu              |
                    +---------------------------------------+
                    | 1. Find book by title                 |
                    | 2. List registered books              |
                    | 3. List registered authors            |
                    | 4. List authors alive in a given year |
                    | 5. List books by language             |
                    | 0. Exit                               |
                    +---------------------------------------+
                    Enter your choice: """;
            System.out.println(menu);
            option = scanner.nextInt();
            scanner.nextLine();
            switch (option) {
                case 1:
                    bookService.findBookByTitle(URL_BASE);
                    break;
                case 2:

                    break;
                case 3:

                    break;
                case 4:

                    break;
                case 5:

                    break;
                case 0:
                    System.out.println("Closing app...");
                    break;
                default:
                    System.out.println("Invalid input. Please enter a number: ");
            }
        }
    }
}
