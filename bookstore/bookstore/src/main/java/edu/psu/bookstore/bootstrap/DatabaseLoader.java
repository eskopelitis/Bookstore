package edu.psu.bookstore.bootstrap;

import edu.psu.bookstore.repository.BookRepository;
import edu.psu.bookstore.Genre;
import edu.psu.bookstore.model.Book;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DatabaseLoader implements ApplicationListener<ContextRefreshedEvent> {

    private final BookRepository bookRepository;

    public DatabaseLoader(BookRepository bookRepository) {

        // spring managed
        this.bookRepository = bookRepository;

        // developer managed
//        gameRepository = new GameRepositoryImpl();
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println("*** SPRING APPLICATION IS STARTING!");
        //bookRepository.addBook(new);
        //bookRepository.addBook((new Book(1231,"hello", 123123123L,"Jack","09/24/1991",Genre.HORROR,.5)));
        //
        //  add games to the database
        //bookRepository.addBook(new Book(123123, "Nevermind", 12341234L,"09/24/1991", "6",Genre.HORROR,30.5));
        //bookRepository.addBook(new Book(123, "In Rainbows", 12341234L, "10/10/2007", Genre.NONFICTION,10,25.0));
        //bookRepository.addBook(new Book(12312312, "Is This It", 12341234L, "07/30/2001", Genre.ROMANCE,8,30.0));
    }
}
