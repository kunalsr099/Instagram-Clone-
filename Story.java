package com.lpu.Momento;


import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Entity
@Table(name = "storys")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Story {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "user_id")   // <- foreign key column in the Post table
	private User user;
	
	@NonNull
	private String image;
	private String caption;
	private LocalDateTime timestamp;
	public Story(Long id, User user, @NonNull String image, String caption, LocalDateTime timestamp) {

		this.id = id;
		this.user = user;
		this.image = image;
		this.caption = caption;
		this.timestamp = timestamp;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getCaption() {
		return caption;
	}
	public void setCaption(String caption) {
		this.caption = caption;
	}
	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
	
	

}
