package com.diegovilca.literalura.service;

import com.diegovilca.literalura.model.Author;
import com.diegovilca.literalura.model.Book;
import com.diegovilca.literalura.model.BookDTO;
import com.diegovilca.literalura.model.Language;
import com.diegovilca.literalura.repository.IAuthorRepository;
import com.diegovilca.literalura.repository.IBookRepository;
import com.diegovilca.literalura.util.UserInputHandler;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.IntStream;


public class AppService {
    private final Scanner scanner;
    private final IBookRepository bookRepository;
    private final IAuthorRepository authorRepository;
    private final ApiService apiService;
    private final UserInputHandler userInputHandler;

    public AppService(IBookRepository iBookRepository, IAuthorRepository iAuthorRepository) {
        scanner = new Scanner(System.in);
        apiService = new ApiService();
        this.bookRepository = iBookRepository;
        this.authorRepository = iAuthorRepository;
        userInputHandler = new UserInputHandler();
    }

    public void findBookByTitle(String url) {
        System.out.println("Enter book's title: ");
        String title = scanner.nextLine();
        List<BookDTO> books = apiService.getDataFromApi(url + "?search=" + title.replace(" ", "%20"));
        List<BookDTO> filteredBooks = filterBooks(books, title);

        System.out.println("\n+---------------------------------------+");
        if (filteredBooks.isEmpty()) {
            System.out.println("No matches found");
            System.out.println("+---------------------------------------+\n");
            scanner.nextLine();
        } else {
            IntStream.range(0, filteredBooks.size())
                    .forEach(i -> {
                        var j = i + 1;
                        System.out.println(j + ". " + filteredBooks.get(i).getTitle() + " (" + filteredBooks.get(i).getLanguages().get(0) + ")");
                    });
            System.out.println("+---------------------------------------+\n");

            int index = this.userInputHandler.getValidatedInt(
                    "Enter the number of the book to save: ",
                    1, filteredBooks.size());

            saveBook(filteredBooks.get(index - 1));
            scanner.nextLine();
        }
    }

    private List<BookDTO> filterBooks(List<BookDTO> bookDTOS, String input) {
        return bookDTOS.stream()
                .filter(bookDTO -> bookDTO.getTitle().toLowerCase().contains(input.toLowerCase()))
                .toList();
    }

    private void saveAuthor(Book book) {
        Optional<Author> dbAuthor = this.authorRepository.findByName(book.getAuthor().getName());
        Author author;
        try {
            if (dbAuthor.isEmpty()) {
                author = book.getAuthor();
            } else {
                author = dbAuthor.get();
            }
            author.addBook(book);
            book.setAuthor(author);

            this.authorRepository.save(author);
        } catch (Exception e) {
            System.out.println("Error, author could not be saved");
            System.out.println(e);
        }
    }

    private void saveBook(BookDTO bookDTO) {
        Book book = new Book(bookDTO);
        try {
            if (!bookExist(book)) {
                saveAuthor(book);
                this.bookRepository.save(book);
                System.out.println("Book '" + book.getTitle() + "' saved");
            } else {
                System.out.println("Selected book already exist. You cannot register a book more than once");
            }

        } catch (Exception e) {
            System.out.println("Error, book could not be saved");
            System.out.println(e);
        }
    }

    private boolean bookExist(Book book) {
        return this.bookRepository.existsByTitle(book.getTitle());
    }

    public void getBooks() {
        List<Book> books = this.bookRepository.findAll();
        if (books.isEmpty()) {
            System.out.println("No books registered");
        } else {
            books.forEach(System.out::println);
        }
        scanner.nextLine();
    }

    public void authorsAliveByYear() {
        Integer year = this.userInputHandler.getValidatedInt(
                "Enter a year: ",
                -1000, LocalDate.now().getYear());
        List<Author> authorsAlive = this.authorRepository.findByBirthYearLessThanEqualAndDeathYearGreaterThanEqual(year, year);

        if (!authorsAlive.isEmpty()) {
            authorsAlive.forEach(System.out::println);
        } else {
            System.out.println("No matches found");
        }
        scanner.nextLine();
    }

    public void getAuthors() {
        List<Author> authors = this.authorRepository.findAll();
        if (authors.isEmpty()) {
            System.out.println("No authors registered");
        } else {
            authors.forEach(System.out::println);
        }
        scanner.nextLine();
    }

    public void getBooksByLanguage() {
        System.out.println("""
                        
                ***************************
                *        Languages        *
                ***************************
                1. English
                2. Spanish
                3. Portuguese
                4. French        
                """);

        try {
            var number = this.userInputHandler.getValidatedInt(
                    "Enter the number of the chosen language:",
                    1, 4);
            List<Book> booksByLanguages = List.of();

            switch (number) {
                case 1:
                    booksByLanguages = this.bookRepository.findBookByLanguage(Language.ENGLISH);
                    break;
                case 2:
                    booksByLanguages = this.bookRepository.findBookByLanguage(Language.SPANISH);
                    break;
                case 3:
                    booksByLanguages = this.bookRepository.findBookByLanguage(Language.PORTUGUESE);
                    break;
                case 4:
                    booksByLanguages = this.bookRepository.findBookByLanguage(Language.FRENCH);
                    break;
                default:
                    System.out.println("Invalid input");
                    number = 0;
            }

            if (number != 0) {
                if (booksByLanguages.isEmpty()) {
                    System.out.println("No matches found");
                } else {
                    booksByLanguages.forEach(System.out::println);
                }
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        scanner.nextLine();
    }
}
