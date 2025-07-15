package com.lpu.Momento;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "posts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Post {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Long id;
	private String caption;
	private String image;
	private String location;
	private LocalDateTime createdAt;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private  User user;
	
	@OneToMany
	private List<Comment> comments = new ArrayList<>();
	
	@ManyToMany
	@JoinTable(
			name = "post_likes",
			joinColumns = @JoinColumn(name = "user_id")
			)
	private Set<User> LikeByUsers = new HashSet<>();

	public Post(Long id, String caption, String image, String location, LocalDateTime createdAt, User user,
			List<Comment> comments, Set<User> likeByUsers) {
		this.id = id;
		this.caption = caption;
		this.image = image;
		this.location = location;
		this.createdAt = createdAt;
		this.user = user;
		this.comments = comments;
		LikeByUsers = likeByUsers;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public Set<User> getLikeByUsers() {
		return LikeByUsers;
	}

	public void setLikeByUsers(Set<User> likeByUsers) {
		LikeByUsers = likeByUsers;
	}
	
	
	
	
	

}
