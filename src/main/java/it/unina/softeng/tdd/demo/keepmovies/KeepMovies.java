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
		// TODO Auto-generated method stub
		return null;
	}

}
