package week01.day03;

import java.time.LocalDate;

public class Movie {

	private Long id;
	private String title;
	private LocalDate dateOfProduction;
	private int length;
	private MovieGenre genre;

	public Movie(Long id, String title, LocalDate dateOfProduction, int length, MovieGenre genre) {
		this.id = id;
		this.title = title;
		this.dateOfProduction = dateOfProduction;
		this.length = length;
		this.genre = genre;
	}

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public LocalDate getDateOfProduction() {
		return dateOfProduction;
	}

	public int getLength() {
		return length;
	}

	public MovieGenre getGenre() {
		return genre;
	}
}
