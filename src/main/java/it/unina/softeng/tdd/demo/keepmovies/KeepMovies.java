package it.unina.softeng.tdd.demo.keepmovies;

import java.util.ArrayList;
import java.util.List;

public class KeepMovies {

	List<Movie> movies = new ArrayList<Movie>();
	
	public List<Movie> getMovies() {
		return movies;
	}

	public void add(Movie movie) {
		if( ! movies.contains(movie) && movie != null ) {
			movies.add(movie);
		}
	}

}
