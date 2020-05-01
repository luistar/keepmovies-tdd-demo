package it.unina.softeng.tdd.demo.keepmovies;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Year;

import org.junit.jupiter.api.Test;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

class MovieSpecification {

	@Test
	void shouldBeMarkedAsNotWatchedByDefault() {
		Movie m = new Movie("1917",Year.of(2019),"war");
		assertThat(m.isAlreadyWatched(), is(false));
	}
	
	@Test
	void shouldBeMarkableAsWatched() {
		Movie m = new Movie("1917",Year.of(2019),"war");
		m.markAsWatched();
		assertThat(m.isAlreadyWatched(), is(true));
	}
}
