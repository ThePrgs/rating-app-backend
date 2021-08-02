package com.nsoft.ratingappbackend.ratingsettings;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entity for ratingSettings
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "rating_settings")
public class RatingSettings {

	/**
	 * Column ID is the primary key of the table
	 */
	@Id
	@Column(name = "id")
	private Long id = 1L;

	/**
	 * Column numOfEmoticons is the set number of emoticons
	 */
	@Min(value = 3, message = "Number of emoticons can't be below 3!")
	@Max(value = 5, message = "Number of emoticons can't be above 5!")
	@Column(name = "num_of_emoticons", nullable = false, columnDefinition = "integer default 3")
	@NotNull
	private Integer numOfEmoticons = 3;

	/**
	 * Column timeout is the number of seconds of the message timeout
	 */
	@Min(value = 0, message = "Timeout can't be a negative value!")
	@Max(value = 10, message = "Timeout can't be above 10!")
	@Column(name = "timeout", nullable = false, columnDefinition = "integer default 5")
	@NotNull
	private Integer timeout = 5;

	/**
	 * Column msg is the set message
	 */
	@Size(min = 3, max = 120, message = "Message can be between 3 and 120 characters.")
	@Column(name = "msg", columnDefinition = "varchar(255) default 'Thank you for rating.'")
	private String msg = "Thank you for your rating";

	public RatingSettings(Integer numOfEmoticons, Integer timeout, String msg){
		this.numOfEmoticons = numOfEmoticons;
		this.timeout = timeout;
		this.msg = msg;
	}

}
