package books_api.books_api.controllers;

import books_api.books_api.dto.BookResponseDTO;
import books_api.books_api.entity.Book;
import books_api.books_api.exception_handler.BookNotFoundException;
import books_api.books_api.services.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/books")
public class BookMvcController {

    private final BookService service;
    private final BookResponseDTO responseDTO;

    @Autowired
    public BookMvcController(BookService service, BookResponseDTO responseDTO) {
        this.service = service;
        this.responseDTO = responseDTO;
    }

    @GetMapping("/list")
    public String findAllBooks(Model model){
        List<Book> retrievedBooks = service.findAll();
        List<BookResponseDTO> parsedBooks = responseDTO.createResponseFromList(retrievedBooks);
        model.addAttribute("parsedBooks", parsedBooks);
        return "book-list";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model){
        Book toBeAddBook = new Book();

        model.addAttribute("book", toBeAddBook);
        return "book-form";
    }

    @PostMapping("/save")
    public String saveEmployee(@Valid @ModelAttribute("book") Book formBook, BindingResult result) throws BookNotFoundException{
        if(result.hasErrors()){
            return "book-form";
        }

        // if what gets on form is an existing book, update it, if a new, add it
        if(formBook.getBookId() != null){
            service.updateBookById(formBook, formBook.getBookId());
        }else{
            service.addBook(formBook);
        }
        return "redirect:/books/list";
    }

    @GetMapping("/showFormToUpdate")
    public String showFormToUpdate(@RequestParam("bookId") Integer bookId, Model model) throws BookNotFoundException {
        Book toUpdateBook = service.getBookById(bookId);
        model.addAttribute("book", toUpdateBook);
        return "book-form";
    }


    @GetMapping("/deleteBookById")
    public String deleteBookById(@RequestParam("bookId") Integer bookId) throws BookNotFoundException{
        service.deleteBookById(bookId);
        return "redirect:/books/list";
    }

}
