package training360.books_app.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import training360.books_app.dtos.BookDto;
import training360.books_app.dtos.CreateBookCommand;
import training360.books_app.services.BookService;

@RestController
@RequestMapping("/api/authors")
@AllArgsConstructor
public class BookController {

    private BookService bookService;

    @PostMapping("/{authorId}/books")
    public BookDto createBook(@PathVariable long authorId, @RequestBody CreateBookCommand command) {
        return bookService.createBook(authorId, command);
    }
}
