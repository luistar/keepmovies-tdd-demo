package it.unina.softeng.tdd.demo.keepmovies;

import java.util.ArrayList;
import java.util.List;

public class KeepMovies {

	List<Movie> movies = new ArrayList<Movie>();
	
	public List<Movie> getMovies() {
		return movies;
	}

	public void add(Movie ... toAdd) {
		if(toAdd != null) {
			for(Movie movie: toAdd) {
				if( movie != null && ! movies.contains(movie) ) {
					movies.add(movie);
				}
			}
		}
	}

	public List<Movie> getMoviesByTitleAsc() {
		List<Movie> sortedList = new ArrayList<Movie>(movies);
		sortedList.sort((Movie m1, Movie m2) -> m1.getTitle().compareTo(m2.getTitle()));
		return sortedList;
	}

	public List<Movie> getMoviesByTitleDesc() {
		List<Movie> sortedList = new ArrayList<Movie>(movies);
		sortedList.sort((Movie m1, Movie m2) -> m1.getTitle().compareTo(m2.getTitle())*-1);
		return sortedList;
	}

	public List<Movie> getMoviesByReleaseYearAsc() {
		List<Movie> sortedList = new ArrayList<Movie>(movies);
		sortedList.sort((Movie m1, Movie m2) -> m1.getReleaseYear().compareTo(m2.getReleaseYear()));
		return sortedList;
	}

	public List<Movie> getMoviesByReleaseYearDesc() {
		List<Movie> sortedList = new ArrayList<Movie>(movies);
		sortedList.sort((Movie m1, Movie m2) -> m1.getReleaseYear().compareTo(m2.getReleaseYear())*-1);
		return sortedList;
	}

}
