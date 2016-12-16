package controllers;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import asg.cliche.Command;
import asg.cliche.Param;
import asg.cliche.Shell;
import asg.cliche.ShellFactory;
import models.Movie;
import models.Rating;
import models.User;
import utils.CSVLoader;
import utils.Serializer;
import utils.XMLSerializer;

public class Main
{
	 public RecommenderAPI likeMovies;
	 
	 public Main() throws Exception
	  {
		// prime();
	    File datastore = new File("datastore.xml");
	    Serializer serializer = new XMLSerializer(datastore);
	    likeMovies = new RecommenderAPI(serializer);
	    likeMovies.prime();
	    likeMovies.load();
	    //likeMovies.prime();
	  }
	 
	 @Command(description="Add a new User")
	 public void addUser (@Param(name="first name") String firstName, @Param(name="last name") String lastName,
	 @Param(name="age") String age, @Param(name="gender") String gender, @Param(name="occupation") String occupation)
	 {
		 likeMovies.addUser(firstName, lastName, age, gender, occupation);
	 }
	 
	 @Command(description = "Get top ten movies")
	 public void topTen(){
		 System.out.println(likeMovies.topTen().values());
	 }
	
	 @Command(description="Delete a User")
	 public void removeUser (@Param(name="id") Long id)
	 {
		 likeMovies.removeUser(id);
	 }
	 
	 @Command(description="Reccomend movies for a user")
	 public void reccomend (@Param(name="id") Long id)
	 {
		// ArrayList<Movie> recs = ;
		 System.out.println(likeMovies.reccomendation(id));
	 }
	 
	 @Command(description="Get a users details")
	 public void getUser (@Param(name="id") Long id)
	 {
		 User user =likeMovies.getUser(id);
		 System.out.println(user);
	 }
	 
	 @Command(description="Get a Users ratings")
	 public void getRatings (@Param(name="id") Long id)
	 {
		 User user=likeMovies.getUser(id);
		 for(Rating rating : user.ratings){
			 System.out.println(rating.toString());
		 }
	 }
	 
	 @Command(description="Delete a Movie")
	 public void removeMovie (@Param(name="id") Long id)
	 {
		 likeMovies.removeMovie(id);
	 }
	 
	 @Command(description="Get a Movies details")
	 public void getMovie (@Param(name="id") Long id)
	 {
		 Movie movie=likeMovies.getMovie(id);
		 System.out.println(movie);
	 }
	 
	 @Command(description="Add a Movie")
	 public void addMovie (@Param(name="title") String title, @Param(name="year") String year, @Param(name="url") String url)
	 {
		 likeMovies.addMovie(title, year, url);
	 }
	 
	 @Command(description="Add a Rating")
	 public void addRating (@Param(name="userId") Long userId, @Param(name="movieId") Long movieId, @Param(name="rating") int rating)
	 {
		 likeMovies.addRating(userId, movieId, rating);
	 }
	 
	 @Command(description="prime")
	 public void prime () throws Exception
	 {
	 likeMovies.prime();
	 }
	 
	 @Command(description="Get all users details")
	  public void getUsers ()
	  {
	    Collection<User> users = likeMovies.getUsers();
	    System.out.println(users);
	  }
	 
	 @Command(description="Get all movies details")
	  public void getMovies ()
	  {
	    Collection<Movie> movies = likeMovies.getMovies();
	    System.out.println(movies);
	  }
	 
	 public static void main(String[] args) throws Exception
	 {
		 Main main = new Main();
		 Shell shell = ShellFactory.createConsoleShell("lm", "Welcome to likemovie - ?help for instructions", main);
		 shell.commandLoop();
		 main.likeMovies.store();
	 }
 }
