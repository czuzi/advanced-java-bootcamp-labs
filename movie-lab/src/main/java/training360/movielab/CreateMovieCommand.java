package training360.movielab;

import lombok.Data;

@Data
public class CreateMovieCommand {

    private String title;
    private int length;
}
