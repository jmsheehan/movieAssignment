package models;

import static org.junit.Assert.*;

import org.junit.Test;

public class RatingTest {

	Rating rating =new Rating(1l, 3l, 4);
	Long one = (long) 1;
	
	 @Test
	  public void testCreate()
	  {
	    assertEquals (Long.valueOf(1),               rating.getUserId());
	    assertEquals (Long.valueOf(3),            	rating.getMovieId());
	    assertEquals (4,   				rating.getRating());     
	  }

	  @Test
	  public void testEquals()
	  {
	    Rating rating2 = new Rating (1l, 3l, 4); 
	    Rating rating3   = new Rating (3l, 2l, 6); 

	    assertEquals(rating, rating);
	    assertEquals(rating, rating2);
	    assertNotEquals(rating, rating3);
	  }
	  
}
