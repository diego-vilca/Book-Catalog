package com.diegovilca.literalura.service;

import com.diegovilca.literalura.model.Author;
import com.diegovilca.literalura.model.Book;
import com.diegovilca.literalura.model.BookDTO;
import com.diegovilca.literalura.repository.IAuthorRepository;
import com.diegovilca.literalura.repository.IBookRepository;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.IntStream;


public class AppService {
    private final Scanner scanner;
    private final IBookRepository bookRepository;
    private final IAuthorRepository authorRepository;
    private final ApiService apiService;

    public AppService(IBookRepository iBookRepository, IAuthorRepository iAuthorRepository) {
        scanner = new Scanner(System.in);
        apiService = new ApiService();
        this.bookRepository = iBookRepository;
        this.authorRepository = iAuthorRepository;
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
            System.out.println("Enter the number of the book to save:");
            int index = scanner.nextInt();
            scanner.nextLine();

            saveBook(filteredBooks.get(index - 1));
            scanner.nextLine();
        }
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

    public void authorsAliveByYear(){
        System.out.println("Enter a year: ");
        Integer year = scanner.nextInt();
        scanner.nextLine();
        List<Author> authorsAlive = this.authorRepository.findByDeathYearGreaterThan(year);

        if (!authorsAlive.isEmpty()){
            authorsAlive.forEach(System.out::println);
        }else{
            System.out.println("No matches found");
        }
        scanner.nextLine();
    }

    private List<BookDTO> filterBooks(List<BookDTO> bookDTOS, String input) {
        return bookDTOS.stream()
                .filter(bookDTO -> bookDTO.getTitle().toLowerCase().contains(input.toLowerCase()))
                .toList();
    }

    public void getAuthors(){
        List<Author> authors = this.authorRepository.findAll();
        if (authors.isEmpty()){
            System.out.println("No authors registered");
        }else{
            authors.forEach(System.out::println);
        }
        scanner.nextLine();
    }

}
