package models;

import com.google.common.base.Objects;

import utils.ToJsonString;

public class Rating {
	private Long movieId;
	private Long userId;
	private int rating;
	
	public Rating(Long userId, Long movieId, int rating) {
		this.userId = userId;
		this.movieId = movieId;
		this.rating = rating;
	}
	
	@Override
	 public int hashCode()
	 {
	 return Objects.hashCode(this.userId, this.movieId, this.rating);
	 }
	
	@Override
	 public String toString()
	 {
	 return new ToJsonString(getClass(), this).toString();
	 }
	
	 @Override
	 public boolean equals(final Object obj)
	 {
	 if (obj instanceof Rating)
	 {
	 final Rating other = (Rating) obj;
	 return Objects.equal(userId, other.userId)
	 && Objects.equal(movieId, other.movieId)
	 && Objects.equal(rating, other.rating);
	 }
	 else
	 {
	 return false;
	 }
	 }
	 
	 public Long getMovieId() {
			return movieId;
		}

		public Long getUserId() {
			return userId;
		}

		public int getRating() {
			return rating;
		}


}
