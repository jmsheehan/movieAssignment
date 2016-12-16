package controllers;


import static models.Fixtures.users;
import static models.Fixtures.movies;
import static models.Fixtures.ratings;
import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import models.Movie;
import models.Rating;
import models.User;
import utils.Serializer;
import utils.XMLSerializer;

public class PersistenceTest
{
  RecommenderAPI recommender;
  
  void populate (RecommenderAPI recommender)
  {
    for (User user : users)
    {
      recommender.addUser(user.firstName, user.lastName, user.gender, user.age, user.occupation);
    }
    for (Movie movie : movies)
    {
      recommender.addMovie(movie.title, movie.year, movie.url);
    }
    for (Rating rating : ratings)
    {
    	recommender.addRating(rating.getUserId(), rating.getMovieId(), rating.getRating());
    }
  }
  
  @Test
  public void testPopulate()
  { 
    recommender = new RecommenderAPI(null);
    assertEquals(0, recommender.getUsers().size());
    populate (recommender);
    
    assertEquals(users.length, recommender.getUsers().size());
    assertEquals(movies.length, recommender.getMovies().size());
    assertEquals(2, recommender.getUser(1l).ratings.size());
    assertEquals(1, recommender.getUser(2l).ratings.size());
 
  }

  void deleteFile(String fileName)
  {
    File datastore = new File ("testdatastore.xml");
    if (datastore.exists())
    {
      datastore.delete();
    }
  }
  
  @Test
  public void testXMLSerializer() throws Exception
  { 
    String datastoreFile = "testdatastore.xml";
    deleteFile (datastoreFile);
    
    Serializer serializer = new XMLSerializer(new File (datastoreFile));
    
    recommender = new RecommenderAPI(serializer); 
    populate(recommender);
    recommender.store();
    
    RecommenderAPI recommender2 =  new RecommenderAPI(serializer);
    recommender2.load();
    
    assertEquals (recommender.getUsers().size(), recommender2.getUsers().size());
    for (User user : recommender.getUsers())
    {
      assertTrue (recommender2.getUsers().contains(user));
    }
    deleteFile ("testdatastore.xml");
  }
  
  
}