package it.unina.softeng.tdd.demo.keepmovies;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Year;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat; 
import static org.hamcrest.Matchers.*;

import static it.unina.softeng.tdd.demo.keepmovies.MovieComparators.*;

class KeepMoviesSpecification {
	
	KeepMovies k;
	Movie joker, jojo, dunkirk, up;
	
	@BeforeEach
	void init() {
		k       = new KeepMovies();
		joker   = new Movie("Joker",Year.of(2019),"thriller");
		jojo    = new Movie("Jojo Rabbit", Year.of(2019), "comedy-drama");
		dunkirk = new Movie("Dunkirk",Year.of(2017),"war");
		up      = new Movie("Up",Year.of(2009),"comedy-drama");
	}
	
	@Test
	void shouldBeEmptyWhenInstantiated() {
		List<Movie> movies = k.getMovies();
		
		assertThat(movies, is(empty()));
	}
	
	@Test
	void shouldStoreSavedMovies() {
		k.add(joker);
		k.add(jojo);
		
		assertThat(k.getMovies(),containsInAnyOrder(joker,jojo));
	}
	
	@Test
	void shouldNotStoreDuplicateMovies() {
		k.add(joker);
		k.add(jojo);
		k.add(joker);
		
		assertThat(k.getMovies(),containsInAnyOrder(joker,jojo));
	}
	
	@Test
	void shouldNotStoreNullMovies() {
		k.add(joker);
		k.add(jojo);
		k.add(null);
		
		assertThat(k.getMovies(),containsInAnyOrder(joker,jojo));
	}
	
	@Test
	void shouldNotStoreNullOrDuplicateMoviesVariadic() {
		k.add(joker,jojo,null,up,joker,jojo);
		k.add(dunkirk,jojo);
		
		assertThat(k.getMovies(),containsInAnyOrder(joker,jojo,dunkirk,up));
	}
	
	@Nested 
	public class SortingTests {
		@Test
		void shouldSortCorrectlyByTitleAsc() {
			k.add(joker,jojo,up,dunkirk);
			
			List<Movie> sortedList = k.getMoviesOrdered(BY_TITLE_ASC);
			
			assertThat(sortedList, contains(dunkirk,jojo,joker,up));
		}
		
		@Test
		void shouldSortCorrectlyByTitleDesc() {
			k.add(joker,jojo,up,dunkirk);
			
			List<Movie> sortedList = k.getMoviesOrdered(BY_TITLE_DESC);
			
			assertThat(sortedList, contains(up,joker,jojo,dunkirk));
		}
		
		@Test
		void shouldSortCorrectlyByReleaseYearAsc() {
			k.add(joker,jojo,up,dunkirk);
			
			List<Movie> sortedList = k.getMoviesOrdered(BY_RELEASE_DATE_ASC);
			
			assertThat(sortedList, anyOf(contains(up,dunkirk,jojo,joker),contains(up,dunkirk,joker,jojo)));
		}
		
		@Test
		void shouldSortCorrectlyByReleaseYearDesc() {
			k.add(joker,up,dunkirk);
			
			List<Movie> sortedList = k.getMoviesOrdered(BY_RELEASE_DATE_DESC);
			
			assertThat(sortedList, contains(joker,dunkirk,up));
		}
	}

	@Test
	void shouldRemoveAlreadyWatchedMoviesCorrectly() {
		k.add(jojo,joker,up,dunkirk);
		up.markAsWatched();
		joker.markAsWatched();
		
		k.removeWatchedMovies();
		
		assertThat(k.getMovies(),containsInAnyOrder(jojo,dunkirk));
	}
	
	@Test
	void removingWatchedMoviesShouldHaveNoEffectIfThereAreNoWatchedMovies() {
		k.add(jojo,joker,up,dunkirk);
		
		k.removeWatchedMovies();
		
		assertThat(k.getMovies(),containsInAnyOrder(jojo,dunkirk,up,joker));
	}
	
	@Test
	void shouldGroupMoviesByYear() {
		k.add(joker,jojo,up,dunkirk);
		
		Map<Year,List<Movie>> grouping = k.groupMoviesByYear();
		
		assertThat(grouping, allOf(
			hasEntry(is(Year.of(2019)), containsInAnyOrder(joker,jojo)),
			hasEntry(is(Year.of(2017)), containsInAnyOrder(dunkirk)),
			hasEntry(is(Year.of(2009)), containsInAnyOrder(up)),
			not(hasKey(allOf(
				is(not(Year.of(2019))),
				is(not(Year.of(2017))),
				is(not(Year.of(2009)))
			)))
		));
	}
	
	@Test
	void shouldReturnEmptyGroupingWhenGroupMoviesByYearIsCalledOnEmptyKeepMovies() {
		
		Map<Year,List<Movie>> grouping = k.groupMoviesByYear();
		
		assertThat(grouping.keySet(), is(empty()));
	}
	
	
	@Test
	void shouldReturnEmptyGroupingWhenGroupMoviesByGenreIsCalledOnEmptyKeepMovies() {
		
		Map<String,List<Movie>> grouping = k.groupMoviesByGenre();
		
		assertThat(grouping.keySet(), is(empty()));
	}
	
	@Test
	void shouldGroupMoviesByGenre() {
		k.add(joker,jojo,up,dunkirk);
		
		Map<String,List<Movie>> grouping = k.groupMoviesByGenre();
		
		assertThat(grouping, allOf(
			hasEntry(is("war"), containsInAnyOrder(dunkirk)),
			hasEntry(is("thriller"), containsInAnyOrder(joker)),
			hasEntry(is("comedy-drama"), containsInAnyOrder(up,jojo)),
			not(hasKey(allOf(
				is(not(equalTo("war"))),
				is(not(equalTo("thriller"))),
				is(not(equalTo("comedy-drama")))
			)))
		));
	}
	
	@Test
	void shouldGroupMoviesByCustomCriteria() {
		k.add(joker,jojo,up,dunkirk);
		Map<Integer,List<Movie>> grouping = k.groupBy( (Movie movie) -> {
			return movie.getReleaseYear().getValue()%10;
		});
		assertThat(grouping, allOf(
			hasEntry(is(9), containsInAnyOrder(joker,jojo,up)),
			hasEntry(is(7), containsInAnyOrder(dunkirk))
		));
	}

}
