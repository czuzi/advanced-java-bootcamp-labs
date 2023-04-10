package training360.books_app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import training360.books_app.model.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
