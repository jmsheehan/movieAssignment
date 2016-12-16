package models;

import static models.Fixtures.users;
import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class UserTest {
	
	//(String firstName, String lastName, String gender, String age, String occupation)
	
	User homer = new User ("homer", "simpson", "M",  "24", "technitian");
	
	@Test
	  public void testCreate()
	  {
	    assertEquals ("homer",               homer.firstName);
	    assertEquals ("simpson",             homer.lastName);
	    assertEquals ("M",   				 homer.gender);   
	    assertEquals ("24",              homer.age);
	    assertEquals ("technitian",			 homer.occupation);
	  }
	
	@Test
	  public void testEquals()
	  {
		User homer2 = new User ("homer", "simpson", "M",  "24", "technitian");
	    User bart   = new User ("bart", "simpson", "M", "24",  "builder"); 

	    assertEquals(homer, homer);
	    assertEquals(homer, homer2);
	    assertNotEquals(homer, bart);
	  } 
}
