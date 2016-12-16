package models;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;

import utils.ToJsonString;

public class Movie
{

 public long id;

 public String title;
 public String year;
 private int sumRatings;
 public String url;
 public Movie(String title, String year, String url)
 {
 this.title = title;
 this.year = year;
 this.url = url;
 }

 public void addRating(Rating rating) {
		Preconditions.checkNotNull(rating);
		sumRatings += rating.getRating();
	}
 
 public int getTotalRating() {
		return this.sumRatings;
	}
 
 @Override
 public String toString()
 {
 return new ToJsonString(getClass(), this).toString();
 }

 @Override
 public int hashCode()
 {
 return Objects.hashCode(this.id, this.title, this.year, this.url);
 }

 @Override
 public boolean equals(final Object obj)
 {
 if (obj instanceof Movie)
 {
 final Movie other = (Movie) obj;
 return Objects.equal(title, other.title)
 && Objects.equal(year, other.year)
 && Objects.equal(url, other.url);
 }
 else
 {
 return false;
 }
 }


public int getSumRatings() {
	return sumRatings;
}


public void setSumRatings(int sumRatings) {
	this.sumRatings = sumRatings;
}

public void setId(long id) {
	this.id = id;
}
}