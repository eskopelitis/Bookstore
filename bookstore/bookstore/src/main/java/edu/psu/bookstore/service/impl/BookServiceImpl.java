package edu.psu.bookstore.service.impl;

import com.google.common.collect.MoreCollectors;
import edu.psu.bookstore.DateTime;
import edu.psu.bookstore.Genre;
import edu.psu.bookstore.model.Book;
import edu.psu.bookstore.repository.BookRepository;
import edu.psu.bookstore.service.BookService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {
    private List<Book> books;
    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {

        //
        //  developer managed instance
//        gameRepository = new GameRepositoryImpl();

        //  spring managed -> constructor injection
        this.books = new ArrayList<>();
        this.bookRepository=bookRepository;
    }

    @Override
    public List<Book> getGames() {
        return books;
    }

    @Override
    public String validateFormSubmit(String bTitle, String bISBN, String bAuthor, String bDatePublished, Genre bGenre, String bPrice) {
        if (!StringUtils.hasText(bTitle)) {
            return "Book Title is required";
        }
        if (!StringUtils.hasText(bISBN)) {
            return "ISBN is required";
        }
        if (!StringUtils.hasText(bAuthor)) {
            return "Author is required";
        }
        if (!StringUtils.hasText(bDatePublished)) {
            return "Date Published is required";
        }
        if ((bGenre==null)) {
            return "Genre is required; Horror,Nonfiction,Romance,Classics";
        }
        if (!StringUtils.hasText(bPrice)) {
            return "Price is required";
        }
            return null;
        }
    @Override
    public void addBook(String bTitle, Long bISBN, String bAuthor, DateTime bDatePublished, Genre bGenre, Double bPrice) {
        books.add(new Book(books.size(), bTitle, bISBN, bAuthor, bDatePublished, bGenre, bPrice));
    }

    @Override
    public void deleteGame(Integer gameId) {
        bookRepository.deleteBook(gameId);
        books=bookRepository.getBooks();
    }

    @Override
    public Book getGameById(Integer gameId) {
        return books.stream().filter(g -> Objects.equals(g.bId(), gameId))
                .collect(MoreCollectors.onlyElement());
    }

    @Override
    public void editBook(Integer bId, String bTitle, Long bISBN, String bAuthor, DateTime bDatePublished, Genre bGenre, Double bPrice) {
        var game = getGameById(bId);
        if (game != null) {
            game.setbTitle(bTitle);
            game.setbISBN(bISBN);
            game.setbAuthor(bAuthor);
            game.setbDatepublishedd(bDatePublished);
            game.setbGenre(bGenre);
            game.setbPrice(bPrice);
        }
    }
}
