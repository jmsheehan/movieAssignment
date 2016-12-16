package controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import com.google.common.base.Optional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import utils.CSVLoader;
import utils.Serializer;
import models.Movie;
import models.Rating;
import models.User;

public class RecommenderAPI {
	private Serializer serializer;
	  
	  private Map<Long,   User>   userIndex       = new HashMap<>();
	  private List<Rating> ratingIndex = new ArrayList();
	  private Map<Long, Movie> movieIndex = new HashMap<>();

	  
	  public RecommenderAPI()
	  {}
	  
	  public RecommenderAPI(Serializer serializer)
	  {
	    this.serializer = serializer;
	  }
	  
	  @SuppressWarnings("unchecked")
	  public void load() throws Exception
	  {
	    serializer.read();
	    movieIndex = (Map<Long, Movie>) serializer.pop();
	    setRatingIndex((List<Rating>)  serializer.pop());
	    userIndex       = (Map<Long, User>)     serializer.pop();
	  }
	  
	  public void store() throws Exception
	  {
	    serializer.push(userIndex);
	    serializer.push(getRatingIndex());
	    serializer.push(movieIndex);
	    serializer.write(); 
	  }
	  
	  public void prime() throws Exception
	  {
	 CSVLoader loader = new CSVLoader();
	 List <User> users = loader.loadUsers("./library/users.dat");
	 List <Movie> movies = loader.loadMovies("./library/items.dat");
	 List <Rating> ratings = loader.loadRatings("./library/ratings.dat");
	 userIndex.clear();
	 for (User user : users)
	 {
		 addUser(user.firstName , user.lastName, user.age, user.gender, user.occupation);
	 }
	 movieIndex.clear();
	 for (Movie movie : movies)
	 {
	 // movieIndex.put(movie.id,movie);
	  addMovie(movie.title, movie.year, movie.url);
	 }
	 for (Rating rating : ratings)
	 {
		 addRating(rating.getUserId(), rating.getMovieId(), rating.getRating());
		 getRatingIndex().add(rating);
	 }
	 
	  }
	  
	  public Rating addRating(Long userID, Long movieID, int rating)
	  {
			 Rating r = new Rating(userID, movieID, rating);
			 userIndex.get(userID).ratings.add(r);
			 Movie m = movieIndex.get(r.getMovieId());
			 if(m != null){
				 m.addRating(r);
			 }
			 return r;
	  }
	 
	  
	  public Collection<User> getUsers ()
	  {
	    return userIndex.values();
	  }
	  
	  public void removeUser(Long userId) 
	  {
	    User user = userIndex.get(userId);
	    userIndex.remove(user);
	  }
	  
	  public User getUser(Long userId) 
	  {
	    User user = userIndex.get(userId);
	    return user;
	  }
	  
	  public User addUser(String firstName, String lastName, String age, String gender, String occupation)
	  {
	  User user = new User (firstName, lastName, gender, age, occupation);
	  user.setId((long) (userIndex.size()+1));
	  userIndex.put(user.userId, user);
	  return user;
	  }
	  
	  public Collection<Movie> getMovies ()
	  {
	    return movieIndex.values();
	  }
	  
	  public void removeMovie(Long movieId) 
	  {
	    Movie movie = movieIndex.get(movieId);
	    userIndex.remove(movie);
	  }
	  
	  public Movie getMovie(Long movieId) 
	  {
	    Movie movie = movieIndex.get(movieId);
	    return movie;
	  }
	  
	  public Movie addMovie(String title, String year, String url)
	  {
	  Movie movie = new Movie (title, year, url);
	  movie.setId((long) (movieIndex.size()+1));
	  movieIndex.put(movie.id, movie);
	  return movie;
	  }
	  
	  public TreeMap<Long, User> similarUsers(Long userId)
	  {
		  Map<Long, User> similars = new HashMap<Long, User>();
		  ArrayList<Integer> a = new ArrayList<Integer>();
		  ArrayList<Integer> b = new ArrayList<Integer>();
		  User userLogged = userIndex.get(userId);
		 // if(userLogged.ratings.size()>0){
			  for(User user : userIndex.values()){
				  long similarity = 0;
				  if(!((user.getId()).equals(userId))){
					  for(Rating rating1 : userLogged.ratings){
						  for(Rating rating2 : user.ratings){
							  if(rating1.getMovieId().equals((rating2.getMovieId())) ){
								  a.add(rating1.getRating());
								  b.add(rating2.getRating());
							  }
						  }
					  }
				  if(a.size()>0){
					  for(int i=0;i<a.size();i++){
						  similarity+= a.get(i) * b.get(i);
					  }
				  }
				 
				  similars.put(similarity, user);
				  a.clear();
				  b.clear();
			  }
			  }
			  TreeMap<Long, User> t =new TreeMap<Long, User>(similars);
				//t.descendingMap();
				return t;
		  
	  }
	  
	  public ArrayList<Movie> reccomendation(Long userId){
		  TreeMap<Long, User> map = similarUsers(userId);
		  map.descendingMap();
		  User userLogged = userIndex.get(userId);
		  ArrayList<Movie> movieRec = new ArrayList<Movie>();
		  if(map.values().size()>0){
			  for(User user : map.values()){
				  if(movieRec.size()<11){
					  ArrayList<Rating> ratings =user.ratings;
					  for(Rating rating : ratings){
						 boolean notIn =true;
						  for(Rating rating2 :  userLogged.ratings){
							  if(rating2.getMovieId()==rating.getMovieId()){
								  notIn =false;
								  break;
							  } 
						  }
						  if(notIn = true){
							  if(movieRec.size()<11)
							  movieRec.add(movieIndex.get(rating.getMovieId()));
						  }
						  
					  }
				  }
			  }
		  }
		  return movieRec;
	  }
	  
	  public Map<Integer,Movie> topTen(){
		  Map<Integer, Movie> sums = new HashMap<Integer, Movie>();
		  for(Movie movie : movieIndex.values()){
			  sums.put(movie.getSumRatings(), movie);
		  }
		  TreeMap<Integer, Movie> map = new TreeMap<>(sums);
		  
		  Map <Integer, Movie> m = map.descendingMap();
		  int i = 0;
		  Map <Integer, Movie> m2 = new HashMap<Integer, Movie>();
		  for(Map.Entry<Integer, Movie> entry : m.entrySet()){
			  
			  if(i<10){
				  m2.put(entry.getKey(), entry.getValue());
				  i++;
			  }
			  else{
				  break;
			  }
			 
		  }
		  TreeMap<Integer, Movie> map2 = new TreeMap<>(m2);
		  Map <Integer, Movie> m3 = map2.descendingMap();
		  return m3;
	  }

	public List<Rating> getRatingIndex() {
		return ratingIndex;
	}

	public void setRatingIndex(List<Rating> ratingIndex) {
		this.ratingIndex = ratingIndex;
	}
	
	public Map<Long, Movie>  getMovieIndex() {
		return movieIndex;
	}

	public void setRatingIndex(Map<Long, Movie> movieIndex) {
		this.movieIndex= movieIndex;
	}
}
