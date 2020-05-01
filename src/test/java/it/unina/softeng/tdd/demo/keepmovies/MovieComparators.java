package it.unina.softeng.tdd.demo.keepmovies;

import java.util.Comparator;

public class MovieComparators {
	public static final Comparator<Movie> BY_RELEASE_DATE_ASC = 
		(Movie a, Movie b) -> a.getReleaseYear().compareTo(b.getReleaseYear());
		
	public static final Comparator<Movie> BY_RELEASE_DATE_DESC = 
		BY_RELEASE_DATE_ASC.reversed();

	public static final Comparator<Movie> BY_TITLE_ASC = 
		(Movie a, Movie b) -> a.getTitle().compareTo(b.getTitle());

	public static final Comparator<Movie> BY_TITLE_DESC = 
		BY_TITLE_ASC.reversed();
}
