package edu.psu.bookstore.repository;

import edu.psu.bookstore.DateTime;
import edu.psu.bookstore.Genre;
import edu.psu.bookstore.model.Book;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.stream.Collectors;

@Repository
public class BookRepositoryImpl implements BookRepository {

    //
    //  instance data
    private List<Book> games;

    public BookRepositoryImpl() {
        games = new ArrayList<>();
    }

    public List<Book> getGames() {
        return games;
    }

    @Override
    public void addBook(Book game) {
        games.add(game);
    }

    @Override
    public List<Book> getBooks() {
        return games;
    }

    @Override
    public List<Book> searchGames(String term) {
        return games.stream().filter(
                g -> g.getbTitle().toLowerCase(Locale.ROOT).contains(term.toLowerCase(Locale.ROOT))
        ).collect(Collectors.toList());
    }

    @Override
    public long findMaxbId() {
        var gameIdList = games.stream().map(Book::bId).collect(Collectors.toList());
        long max = Integer.MIN_VALUE;
        for (long i : gameIdList) {
            max = Math.max(max, i);
        }

        return max;
    }

    @Override
    public Book getBookById(Integer gameId) {
        var filtered = games.stream().filter(g -> Objects.equals(g.bId(), gameId)).collect(Collectors.toList());
        if (filtered.size() > 0) {
            return filtered.get(0);
        }
        throw new IllegalStateException("game not found with ID " + gameId);
    }

    @Override
    public void editBook(Book book, Integer  bId, String bTitle, Long bISBN, String bAuthor, DateTime bDatePublished, Genre bGenre, Double bPrice) {
        book.setbId(bId);
        book.setbTitle(bTitle);
        book.setbISBN(bISBN); ;
        book.setbAuthor(bAuthor);
        book.setbGenre(bGenre);
        book.setbPrice(bPrice);
    }


    @Override
    public void deleteBook(Integer gameId) {
        games = games.stream().filter(g -> !Objects.equals(g.bId(), gameId)).collect(Collectors.toList());
    }
}
