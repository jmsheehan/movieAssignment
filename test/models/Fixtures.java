package models;

public class Fixtures {
	
	public static User[] users =
		  {
		    new User ("marge", "simpson", "F", "24", "job1"),
		    new User ("lisa",  "simpson", "F",  "44","job2"),
		    new User ("bart",  "simpson", "M",  "34","job3"),
		    new User ("maggie","simpson", "F", "900","job4")
		  };

		  public static Movie[] movies =
		  {
		    new Movie ("movie1",  "1999", "one.com"),
		    new Movie ("movie2",  "299",    "two.com"),
		    new Movie ("movie3",   "3232",   "three.com"),
		    new Movie ("movie4",  "4242",  "four.com"),
		    new Movie ("movie5", "19994", "five.com")
		  };

		  public static Rating[]ratings =
		  {
		    new Rating(1l,2l,5),
		    new Rating(2l,3l,3),  
		    new Rating(1l, 1l, -4),
		    new Rating(4l, 1l, 2)       
		  };
}
