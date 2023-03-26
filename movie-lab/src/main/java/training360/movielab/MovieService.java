package training360.movielab;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class MovieService {

    private ModelMapper mapper;

    private AtomicLong atomicLong = new AtomicLong();
    private List<Movie> movies = new ArrayList<>();

    public MovieService(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public List<MovieDto> getMovies() {
        Type targetListType = new TypeToken<List<MovieDto>>(){}.getType();
        return mapper.map(movies, targetListType);
    }

    public MovieDto createMovie(CreateMovieCommand command) {
        Movie movie = new Movie(atomicLong.incrementAndGet(), command.getTitle(), command.getLength());
        movies.add(movie);
        return mapper.map(movie, MovieDto.class);
    }
}
