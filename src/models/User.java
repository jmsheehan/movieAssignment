package models;
import static com.google.common.base.MoreObjects.toStringHelper;

import java.awt.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.google.common.base.Objects;

import utils.ToJsonString;


public class User
{
 public Long userId;
 public String firstName;
 public String lastName;
 public String gender;
 public String age;
 public String occupation;

 public ArrayList<Rating> ratings = new ArrayList<>();

 public User(String firstName, String lastName, String gender, String age, String occupation)
 {
 this.firstName = firstName;
 this.lastName = lastName;
 this.gender = gender;
 this.age = age;
 this.occupation = occupation;
 }

 public String toString()
 {
 return new ToJsonString(getClass(), this).toString();
 }

 @Override
 public int hashCode()
 {
 return Objects.hashCode(this.lastName, this.firstName, this.gender, this.age);
 }
 
 public String getFirstName() {
		return firstName;
	}
 
 public Long getId() {
		return userId;
	}
 
 public void setId(Long userId) {
		this.userId = userId;
	}

 @Override
 public boolean equals(final Object obj)
 {
 if (obj instanceof User)
 {
 final User other = (User) obj;
 return Objects.equal(firstName, other.firstName)
 && Objects.equal(lastName, other.lastName)
 && Objects.equal(gender, other.gender)
 && Objects.equal(age, other.age)
 && Objects.equal(occupation, other.occupation)
 && Objects.equal(ratings, other.ratings);
 }
 else
 {
 return false;
 }
 }
}