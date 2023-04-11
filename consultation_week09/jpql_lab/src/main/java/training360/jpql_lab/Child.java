package training360.jpql_lab;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "children")
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Setter
@Getter
public class Child {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private final String name;

    @Column(name = "year_of_birth")
    private final int yearOfBirth;
    @ManyToOne
    private Person person;
}
