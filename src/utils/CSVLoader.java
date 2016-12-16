package utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

import edu.princeton.cs.introcs.In;
import models.Movie;
import models.Rating;
import models.User;

public class CSVLoader
{
	public List<User> loadUsers(String filename) throws Exception
	 {
	 File usersFile = new File(filename);
	 In inUsers = new In(usersFile);

	 String delims = "[|]";
	 List<User> users = new ArrayList<User>();
	 while (!inUsers.isEmpty())
	 {
	 String userDetails = inUsers.readLine();
	 String[] userTokens = userDetails.split(delims);
	 if (userTokens.length == 7)
	 {
	 Long id = Long.valueOf(userTokens[0]);
	 String firstName = userTokens[1];
	 String lastName = userTokens[2];
	 String age = userTokens[3];
	 String gender = userTokens[4];
	 String occupation = userTokens[5];

	 users.add(new User(firstName, lastName, gender, age, occupation));
	 }
	 else
	 {
	 throw new Exception("Invalid member length: " + userTokens.length);
	 }
	 }
	 return users;
	 }

 public List<Movie> loadMovies (String filename) throws Exception
 {
	 File moviesFile = new File(filename);
	 In inMovies = new In(moviesFile);

	 String delims = "[|]";
	 List<Movie> movies = new ArrayList<Movie>();
	 while (!inMovies.isEmpty())
	 {
	 String userDetails = inMovies.readLine();
	 String[] userTokens = userDetails.split(delims);
	 //if (userTokens.length == 24)
	 //{
		 Long id = Long.valueOf(userTokens[0]);
	 String title = userTokens[1];
	 String date = userTokens[2];
	 String url = userTokens[3];

	 movies.add(new Movie(title,date,url));
	 //}
	 //else
	 //{
	 //throw new Exception("Invalid member length: " + userTokens.length);
	 //}
	 }
	 
	 return movies;
 }
 public List<Rating> loadRatings (String filename) throws Exception
 {
	 File ratingsFile = new File(filename);
	 In inRatings = new In(ratingsFile);

	 String delims = "[|]";
	 List<Rating> ratings = new ArrayList<Rating>();
	 while (!inRatings.isEmpty())
	 {
	 String userDetails = inRatings.readLine();
	 userDetails.trim();
	 
	 String[] userTokens = userDetails.split(delims);
	 if (userTokens.length == 4)
	 {
		 long userId = Long.valueOf(userTokens[0]);
		 long movieId = Long.valueOf(userTokens[1]);
		 int rating = Integer.valueOf(userTokens[2]);

	 ratings.add(new Rating(userId,movieId, rating));
	 }
	 else
	 {
	 throw new Exception("Invalid member length: " + userTokens.length);
	 }
	 }
	 return ratings;
 }
}
