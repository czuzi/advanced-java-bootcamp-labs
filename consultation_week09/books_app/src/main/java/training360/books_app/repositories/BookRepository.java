package training360.books_app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import training360.books_app.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}
