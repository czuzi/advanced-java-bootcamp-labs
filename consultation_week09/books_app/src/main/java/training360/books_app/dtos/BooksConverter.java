package training360.books_app.dtos;

import org.mapstruct.Mapper;
import training360.books_app.dtos.AuthorDto;
import training360.books_app.model.Author;
import training360.books_app.model.Book;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BooksConverter {
    AuthorDto convert(Author author);
    BookDto convert(Book book);
    List<AuthorDto> convert(List<Author> all);

    List<BookDto> convertBooks(List<Book> books);
}
