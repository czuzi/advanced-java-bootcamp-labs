package training360.jpql_lab;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private final String name;
    private final int age;
    @OneToMany(mappedBy = "person")
    List<Child> children = new ArrayList<>();

    public void addChild(Child child) {
        children.add(child);
        child.setPerson(this);
    }
}
