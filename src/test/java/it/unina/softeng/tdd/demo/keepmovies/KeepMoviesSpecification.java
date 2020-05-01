package it.unina.softeng.tdd.demo.keepmovies;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Year;
import java.util.List;

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
}
