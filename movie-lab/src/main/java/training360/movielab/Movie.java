package training360.movielab;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Movie {

    private Long id;

    private String title;

    private int length;

    private List<Double> ratings = new ArrayList<>();

    private double avgRating;

    public Movie(Long id, String title, int length) {
        this.id = id;
        this.title = title;
        this.length = length;
    }
}
