package models;

import static org.junit.Assert.*;

import org.junit.Test;

public class MovieTest {
	
	Movie movie = new Movie ("movie1",  "1999", "one.com");
	Movie movie2 = new Movie ("movie1",  "1999", "one.com");
	Movie movie3 = new Movie ("movie2",  "1599", "four.com");
	
	Rating rating1 = new Rating(1l, 3l, 4);
	Rating rating2 = new Rating(3l, 3l, 3);

	@Test
	public void testCreate() {
		assertEquals ("movie1",               movie.title);
	    assertEquals ("1999",             movie.year);
	    assertEquals ("one.com",   				 movie.url);   
	}
	
	@Test
	public void testEquals()
	{
		assertEquals(movie, movie);
		assertEquals(movie, movie2);
		assertNotEquals(movie, movie3);
	}
	
	@Test
	public void testSum(){
		movie.addRating(rating1);
		assertEquals(movie.getSumRatings(), 4);
		
		movie.addRating(rating2);
		assertEquals(movie.getSumRatings(), 7);
	}
	

}
