package it.unina.softeng.tdd.demo.keepmovies;

import java.time.Year;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
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
		Map<Year, List<Movie>> grouping = new HashMap<Year, List<Movie>>();
		for(Movie m : movies) {
			Year y = m.getReleaseYear();
			if( grouping.containsKey(y) ) {
				grouping.get(y).add(m);
			}
			else {
				List<Movie> list = new ArrayList<Movie>();
				list.add(m);
				grouping.put(y, list);
			}
		}
		return grouping;
	}

	public Map<String, List<Movie>> groupMoviesByGenre() {
		Map<String, List<Movie>> grouping = new HashMap<String, List<Movie>>();
		for(Movie m : movies) {
			String genre = m.getGenre();
			if( grouping.containsKey(genre) ) {
				grouping.get(genre).add(m);
			}
			else {
				List<Movie> list = new ArrayList<Movie>();
				list.add(m);
				grouping.put(genre, list);
			}
		}
		return grouping;
	}

	public Map<Integer, List<Movie>> groupBy(Object object) {
		// TODO Auto-generated method stub
		return null;
	}

}
