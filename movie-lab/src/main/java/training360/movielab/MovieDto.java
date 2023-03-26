package training360.movielab;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class MovieDto {

    private Long id;

    private String title;

    private int length;

    private List<Double> ratings = new ArrayList<>();

    private double avgRating;
}
