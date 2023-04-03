package training360.nav.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Appointment {

    private Long id;
    private String cdv;
    private LocalDateTime start;
    private LocalDateTime end;
    private NavType type;
}
