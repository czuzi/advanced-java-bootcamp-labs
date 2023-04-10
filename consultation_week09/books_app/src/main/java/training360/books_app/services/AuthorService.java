package training360.books_app.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import training360.books_app.dtos.AuthorDto;
import training360.books_app.dtos.BooksConverter;
import training360.books_app.dtos.CreateAuthorCommand;
import training360.books_app.model.Author;
import training360.books_app.repositories.AuthorRepository;

@Service
@AllArgsConstructor
public class AuthorService {

    private AuthorRepository authorRepository;
    private BooksConverter booksConverter;

    public AuthorDto createAuthor(CreateAuthorCommand command) {
        Author author = new Author(command.getName());
        authorRepository.save(author);
        return booksConverter.convert(author);
    }
}
