package edu.psu.bookstore.controller;

import edu.psu.bookstore.DateTime;
import edu.psu.bookstore.Genre;
import edu.psu.bookstore.model.Book;
import edu.psu.bookstore.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Locale;

@Controller
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/")
    public String index(Model model) {
        List<Book> bookList = bookService.getGames();
        model.addAttribute("bookList", bookList);
        return "index";
    }
    @GetMapping(value = "/search")
    public String search(Model model, @RequestParam String search) {
        List<Book> bookList = bookService.getGames();
        //
        //  filter these out
        List<Book> filtered = bookList.stream()
                .filter(g -> g.getbTitle().toLowerCase().contains(search.toLowerCase(Locale.ROOT))||g.getbAuthor().toLowerCase().contains(search.toLowerCase(Locale.ROOT)))
                .toList();

        model.addAttribute("bookList", filtered);
        return "viewBooks";
    }
    //
    @GetMapping("/admin/books/view")
    public String view(Model model) {
        List<Book> bookList = bookService.getGames();
        model.addAttribute("bookList", bookList);
        return "viewBooks";
    }
    //  add a game
    @GetMapping("/admin/books/add")
    public String addBook() {
        return "addBook";
    }

    @PostMapping("/admin/books/add")
    public String addBookSubmit(Model model, @RequestParam String bTitle, @RequestParam String bISBN, @RequestParam String bAuthor, @RequestParam String bDatePublished, @RequestParam String bGenreInput, @RequestParam String bPrice) {

        //
        //  try to add the game to the DB
        try {
            Genre genre = null;
            bGenreInput = bGenreInput.toLowerCase(Locale.ROOT);
            System.out.println(bGenreInput);
            if (bGenreInput.equals("horror")){
                genre = Genre.HORROR;
            }else if (bGenreInput.equals("nonfiction")){
                genre = Genre.NONFICTION;
            }else if (bGenreInput.equals("romance")){
                genre = Genre.ROMANCE;
            }else if (bGenreInput.equals("classics")) {
                genre = Genre.CLASSICS;
            }
            String bookVal = bookService.validateFormSubmit(bTitle, bISBN, bAuthor, bDatePublished, genre, bPrice);
            if (bookVal!=null) {
                throw new Exception(bookVal);
            }
            DateTime datePublished = new DateTime();
            datePublished.DateTime(bDatePublished);
            bookService.addBook(bTitle, Long.parseLong(bISBN),bAuthor,datePublished,genre,Double.parseDouble(bPrice));
        } catch (Exception ex) {
            model.addAttribute("bTitle", bTitle);
            model.addAttribute("bISBN", bISBN);
            model.addAttribute("bAuthor", bAuthor);
            model.addAttribute("bDatePublished", bDatePublished);
            model.addAttribute("bGenreInput", bGenreInput);
            model.addAttribute("bPrice", bPrice);
            model.addAttribute("errorMessage", ex.getMessage());
//            System.err.println(ex.getMessage());
            return "addBook";
        }

        model.addAttribute("successMessage", "The book was successfully saved");
        return "addBook";
    }

    //
    //  edit a game
    @GetMapping("/admin/books/edit/{bookId}")
    public String editGameView(@PathVariable Integer bookId, Model model) {
        Book book = bookService.getGameById(bookId);
        model.addAttribute("bTitle", book.getbTitle());
        model.addAttribute("bISBN", book.getbISBN());
        model.addAttribute("bAuthor", book.getbAuthor());
        model.addAttribute("bDatePublished", book.getbDatepublished().getDateTimeSTD());
        model.addAttribute("bGenreInput", book.getbGenre());
        model.addAttribute("bPrice", book.getbPrice());
        return "editBook";
    }

    @PostMapping("/admin/books/edit/{bookId}")
    public String editGameSubmit(@PathVariable Integer bookId, Model model, @RequestParam String bTitle, @RequestParam String bISBN, @RequestParam String bAuthor, @RequestParam String bDatePublished, @RequestParam String bGenreInput, @RequestParam String bPrice) {
        try {
            Genre genre = null;
            bGenreInput = bGenreInput.toLowerCase(Locale.ROOT);
            System.out.println(bGenreInput);
            if (bGenreInput.equals("horror")){
                genre = Genre.HORROR;
            }else if (bGenreInput.equals("nonfiction")){
                genre = Genre.NONFICTION;
            }else if (bGenreInput.equals("romance")){
                genre = Genre.ROMANCE;
            }else if (bGenreInput.equals("classics")) {
                genre = Genre.CLASSICS;
            }
            String bookVal = bookService.validateFormSubmit(bTitle, bISBN, bAuthor, bDatePublished, genre, bPrice);
            if (bookVal!=null) {
                throw new Exception(bookVal);
            }
            DateTime datePublished = new DateTime();
            datePublished.DateTime(bDatePublished);
            bookService.editBook(bookId, bTitle, Long.parseLong(bISBN),bAuthor,datePublished,genre,Double.parseDouble(bPrice));
        } catch (Exception ex) {
            model.addAttribute("bTitle", bTitle);
            model.addAttribute("bISBN", bISBN);
            model.addAttribute("bAuthor", bAuthor);
            model.addAttribute("bDatePublished", bDatePublished);
            model.addAttribute("bGenreInput", bGenreInput);
            model.addAttribute("bPrice", bPrice);
            model.addAttribute("errorMessage", ex.getMessage());
            return "editBook";
        }
//            System.err.println(ex.getMessage());
        return "redirect:/admin/books/view";
    }

    //
    //  delete a game
    @GetMapping("/admin/books/delete/{bookId}")
    public String deleteGame(Model model, @PathVariable Integer bookId) {
        //
        //  submit the form
        try {
            bookService.deleteGame(bookId);
        } catch (Exception ex) {
            model.addAttribute("error", ex.getMessage());
        }
        List<Book> gameList = bookService.getGames();
        model.addAttribute("gameList", gameList);
        return "redirect:/admin/books/view";

        //
        //  success
//        return "index";
        //return "redirect:/";
    }

}
