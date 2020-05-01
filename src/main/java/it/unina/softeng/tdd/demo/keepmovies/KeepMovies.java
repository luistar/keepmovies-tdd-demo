package it.unina.softeng.tdd.demo.keepmovies;

import java.time.Year;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

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

	public List<Movie> getMoviesOrdered(Comparator<Movie> byCriteria) {
		List<Movie> sortedList = new ArrayList<Movie>(movies);
		sortedList.sort(byCriteria);
		return sortedList;
	}

	public void removeWatchedMovies() {
		movies.removeIf( (Movie m) -> m.isAlreadyWatched());
	}

	public Map<Year, List<Movie>> groupMoviesByYear() {
		// TODO Auto-generated method stub
		return null;
	}

}
