package edu.psu.bookstore.service;

import edu.psu.bookstore.DateTime;
import edu.psu.bookstore.Genre;
import edu.psu.bookstore.Genre;
import edu.psu.bookstore.model.Book;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface BookService {
    List<Book> getGames();

    void addBook(String bTitle, Long bISBN, String bAuthor, DateTime bDatePublished, Genre bGenre, Double bPrice);

    Book getGameById(Integer gameId);

    String validateFormSubmit(String bTitle, String bISBN, String bAuthor, String bDatePublished, Genre bGenre, String bPrice);

    void editBook(Integer bookId, String bTitle, Long bISBN, String bAuthor, DateTime bDatePublished, Genre bGenre, Double bPrice);

    void deleteGame(Integer bookId);
}
