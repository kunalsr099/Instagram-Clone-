package com.lpu.Momento;

import java.time.LocalDateTime;
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
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "comment")
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
public class Comment {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Long id;
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	private String content;
	
	@ManyToMany
	@JoinTable(
			name = "comment_likes",
			joinColumns = @JoinColumn(name = "comment_id"),
			inverseJoinColumns = @JoinColumn(name = "user_id")
			)
	private Set<User> likedByUsers = new HashSet<>();
	private LocalDateTime createdAt;
	public Comment(Long id, User user, String content, Set<User> likedByUsers, LocalDateTime createdAt) {
	
		this.id = id;
		this.user = user;
		this.content = content;
		this.likedByUsers = likedByUsers;
		this.createdAt = createdAt;
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Set<User> getLikedByUsers() {
		return likedByUsers;
	}
	public void setLikedByUsers(Set<User> likedByUsers) {
		this.likedByUsers = likedByUsers;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	
	
	
	
	

}
