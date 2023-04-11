package training360.jpql_lab;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "children")
public class Child {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "year_of_birth")
    private LocalDate yearOfBirth;
    @ManyToOne
    private Person parent;
}
