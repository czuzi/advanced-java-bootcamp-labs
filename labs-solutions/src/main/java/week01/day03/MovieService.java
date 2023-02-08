package week01.day03;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MovieService {

	private List<Movie> movies;

	public void addMovie(Movie movie) {
		if (movie.getDateOfProduction().isAfter(LocalDate.of(1911,1,1))) {
			movies.add(movie);
		}
	}

	public Movie findMovieByTitle(String title) {
		return movies.stream()
				.filter(m -> m.getTitle().equals(title))
				.findAny()
				.orElseThrow(IllegalArgumentException::new);
	}

	public List<Movie> findMoviesAfterDate(LocalDate date) {
		return movies.stream()
				.filter(m -> m.getDateOfProduction().isAfter(date))
				.toList();
	}

	public Map<MovieGenre, List<Movie>> collectMoviesByGenre() {
		return movies.stream()
				.collect(Collectors.groupingBy(Movie::getGenre));
	}
}
