package edu.psu.bookstore.repository;

import edu.psu.bookstore.DateTime;
import edu.psu.bookstore.Genre;
import edu.psu.bookstore.model.Book;

import java.util.List;

public interface BookRepository {
    List<Book> getBooks();

    void addBook(Book book);

    List<Book> searchGames(String term);

    long findMaxbId();

    Book getBookById(Integer bId);

    void editBook(Book book, Integer  bId, String bTitle, Long bISBN, String bAuthor, DateTime bDatePublished, Genre bGenre, Double bPrice);


    void deleteBook(Integer bId);

}
