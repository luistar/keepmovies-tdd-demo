package it.unina.softeng.tdd.demo.keepmovies;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Year;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat; 
import static org.hamcrest.Matchers.*;

class KeepMoviesSpecification {
	
	void someMoviesInCaseYouNeedThem() {
		Movie joker, jojo, dunkirk, up;
		joker   = new Movie("Joker",Year.of(2019),"thriller");
		jojo    = new Movie("Jojo Rabbit", Year.of(2019), "comedy-drama");
		dunkirk = new Movie("Dunkirk",Year.of(2017),"war");
		up      = new Movie("Up",Year.of(2009),"comedy-drama");
	}
	
	@Test
	void shouldBeEmptyWhenInstantiated() {
		KeepMovies k  = new KeepMovies();
		List<Movie> movies = k.getMovies();
		assertThat(movies, is(empty()));
	}
	
	@Test
	void shouldStoreSavedMovies() {
		KeepMovies k  = new KeepMovies();
		Movie joker, jojo;
		joker   = new Movie("Joker",Year.of(2019),"thriller");
		jojo    = new Movie("Jojo Rabbit", Year.of(2019), "comedy-drama");
		
		k.add(joker);
		k.add(jojo);
		
		assertThat(k.getMovies(),containsInAnyOrder(joker,jojo));
	}
	
	@Test
	void shouldNotStoreDuplicateMovies() {
		KeepMovies k  = new KeepMovies();
		Movie joker, jojo;
		joker   = new Movie("Joker",Year.of(2019),"thriller");
		jojo    = new Movie("Jojo Rabbit", Year.of(2019), "comedy-drama");
		
		k.add(joker);
		k.add(jojo);
		k.add(joker);
		
		assertThat(k.getMovies(),containsInAnyOrder(joker,jojo));
	}
	
}
