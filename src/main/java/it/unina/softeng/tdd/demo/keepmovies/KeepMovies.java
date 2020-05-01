package it.unina.softeng.tdd.demo.keepmovies;

import java.time.Year;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

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
		return groupBy((Movie movie) -> movie.getReleaseYear());
	}

	public Map<String, List<Movie>> groupMoviesByGenre() {
		return groupBy((Movie movie) -> movie.getGenre());
	}

	public <T> Map<T, List<Movie>> groupBy(Function<Movie, T> function) {
		Map<T, List<Movie>> grouping = new HashMap<T, List<Movie>>();
		for(Movie m : movies) {
			T key = function.apply(m);
			if( grouping.containsKey(key) ) {
				grouping.get(key).add(m);
			}
			else {
				List<Movie> list = new ArrayList<Movie>();
				list.add(m);
				grouping.put(key, list);
			}
		}
		return grouping;
	}

}
