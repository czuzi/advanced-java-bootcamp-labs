package trainings;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "trainings")
@NoArgsConstructor
@AllArgsConstructor
public class Training {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;
    @Getter
    @Setter
    private String title;
    @Getter
    @Setter
    @Column(name = "start_date")
    private LocalDate startDate;
    @Getter
    @Setter
    @Column(name = "end_date")
    private LocalDate endDate;
    @Getter
    @Setter
    @ManyToOne
    private Trainer trainer;

    public Training(String title, LocalDate startDate, LocalDate endDate, Trainer trainer) {
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.trainer = trainer;
    }
}
