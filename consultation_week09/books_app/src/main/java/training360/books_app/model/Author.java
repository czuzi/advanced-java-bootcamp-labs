package training360.books_app.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@NoArgsConstructor(force = true)
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "authors")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private final String name;
    @OneToMany(mappedBy = "author")
    private List<Book> books;
}
