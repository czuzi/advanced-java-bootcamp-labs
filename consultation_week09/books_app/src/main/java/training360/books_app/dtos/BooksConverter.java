package training360.books_app.dtos;

import org.mapstruct.Mapper;
import training360.books_app.dtos.AuthorDto;
import training360.books_app.model.Author;

@Mapper(componentModel = "spring")
public interface BooksConverter {
    AuthorDto convert(Author author);
}
