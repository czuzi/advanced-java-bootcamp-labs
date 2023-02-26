package trainings;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "trainers")
@NoArgsConstructor
@AllArgsConstructor
public class Trainer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Getter @Setter
    private String name;
    @Getter @Setter
    private Status status;
    @OneToMany(mappedBy = "trainer", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    private List<Training> trainings;

    public Trainer(String name, Status status) {
        this.name = name;
        this.status = status;
    }

    public List<Training> getTrainings() {
        return new ArrayList<>(trainings);
    }

    public void addTraining(Training training) {
        trainings.add(training);
        training.setTrainer(this);
    }
}
