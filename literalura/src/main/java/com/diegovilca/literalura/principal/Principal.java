package com.diegovilca.literalura.principal;

import com.diegovilca.literalura.repository.IAuthorRepository;
import com.diegovilca.literalura.repository.IBookRepository;
import com.diegovilca.literalura.service.AppService;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class Principal {
    private final String URL_BASE = "https://gutendex.com/books/";
    private final Scanner scanner = new Scanner(System.in);
    private final AppService bookService;

    public Principal(IBookRepository iBookRepository, IAuthorRepository iAuthorRepository) {
        bookService = new AppService(iBookRepository, iAuthorRepository);
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
                    bookService.getBooks();
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
