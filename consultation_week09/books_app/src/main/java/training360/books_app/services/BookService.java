package training360.books_app.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import training360.books_app.dtos.BookDto;
import training360.books_app.dtos.BooksConverter;
import training360.books_app.dtos.CreateBookCommand;
import training360.books_app.model.Author;
import training360.books_app.model.Book;
import training360.books_app.repositories.AuthorRepository;
import training360.books_app.repositories.BookRepository;

@Service
@AllArgsConstructor
public class BookService {

    private BookRepository bookRepository;

    private AuthorRepository authorRepository;

    private BooksConverter converter;

    public BookDto createBook(long id, CreateBookCommand command) {
        Author author = authorRepository.getReferenceById(id);
        Book book = new Book(command.getIsbn(), command.getTitle(), author);
        bookRepository.save(book);
        return converter.convert(book);
    }
}
