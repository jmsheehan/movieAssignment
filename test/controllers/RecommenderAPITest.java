package controllers;

import static models.Fixtures.users;
import static models.Fixtures.ratings;
import static models.Fixtures.movies;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;

import controllers.RecommenderAPI;
import models.User;

import org.junit.Test;

public class RecommenderAPITest {
	private RecommenderAPI recommender;
	
	@Before
	  public void setup()
	  {
	    recommender = new RecommenderAPI();
	    for (User user : users)
	    {
	      recommender.addUser(user.firstName, user.lastName, 
	    		  user.gender, user.age, user.occupation);
	    }
	  }
	
	@After
	  public void tearDown()
	  {
	    recommender = null;
	  }
	
	@Test
	  public void testUser()
	  {
	    assertEquals (users.length, recommender.getUsers().size());
	    recommender.addUser("homer", "simpson", "M",  "24", "technitian");
	    assertEquals (users.length+1, recommender.getUsers().size());
	    assertEquals (users[0], recommender.getUser(1l));
	  }  

	  @Test
	  public void testUsers()
	  {
	    assertEquals (users.length, recommender.getUsers().size());
	    for (User user: users)
	    {
	      User eachUser = recommender.getUser(user.userId);
	      assertEquals (user, eachUser);
	      assertNotSame(user, eachUser);
	    }
	  }
	

}
