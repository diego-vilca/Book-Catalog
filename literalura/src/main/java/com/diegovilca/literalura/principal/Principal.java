package com.diegovilca.literalura.principal;

import com.diegovilca.literalura.repository.IAuthorRepository;
import com.diegovilca.literalura.repository.IBookRepository;
import com.diegovilca.literalura.service.AppService;
import com.diegovilca.literalura.util.UserInputHandler;

public class Principal {
    private final String URL_BASE = "https://gutendex.com/books/";
    private final AppService appService;
    private final UserInputHandler userInputHandler;

    public Principal(IBookRepository iBookRepository, IAuthorRepository iAuthorRepository) {
        appService = new AppService(iBookRepository, iAuthorRepository);
        userInputHandler = new UserInputHandler();
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

            option = this.userInputHandler.getValidatedInt(menu, 0, 5);

            switch (option) {
                case 1:
                    appService.findBookByTitle(URL_BASE);
                    break;
                case 2:
                    appService.getBooks();
                    break;
                case 3:
                    appService.getAuthors();
                    break;
                case 4:
                    appService.authorsAliveByYear();
                    break;
                case 5:
                    appService.getBooksByLanguage();
                    break;
                case 0:
                    System.out.println("Closing app...\n");
                    this.userInputHandler.close();
                    break;
                default:
                    System.out.println("Invalid input. Please enter a valid number: ");
            }
        }
    }
}
