package com.lpu.Momento;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String name;
    private String email;
    private String mobile;
    private String bio;
    private String gender;
    private String image;
    private String password;

    @ManyToMany
    private Set<User> follower = new HashSet<>();

    @ManyToMany
    private Set<User> following = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Story> stories = new ArrayList<>();

    @ManyToMany
    private List<Post> savedPost = new ArrayList<>();

    
    public User() {}

    
    public User(Long id, String username, String name, String email, String mobile, String bio, String gender,
                String image, String password, Set<User> follower, Set<User> following, List<Story> stories,
                List<Post> savedPost) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.bio = bio;
        this.gender = gender;
        this.image = image;
        this.password = password;
        this.follower = follower;
        this.following = following;
        this.stories = stories;
        this.savedPost = savedPost;
    }


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getMobile() {
		return mobile;
	}


	public void setMobile(String mobile) {
		this.mobile = mobile;
	}


	public String getBio() {
		return bio;
	}


	public void setBio(String bio) {
		this.bio = bio;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public String getImage() {
		return image;
	}


	public void setImage(String image) {
		this.image = image;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public Set<User> getFollower() {
		return follower;
	}


	public void setFollower(Set<User> follower) {
		this.follower = follower;
	}


	public Set<User> getFollowing() {
		return following;
	}


	public void setFollowing(Set<User> following) {
		this.following = following;
	}


	public List<Story> getStories() {
		return stories;
	}


	public void setStories(List<Story> stories) {
		this.stories = stories;
	}


	public List<Post> getSavedPost() {
		return savedPost;
	}


	public void setSavedPost(List<Post> savedPost) {
		this.savedPost = savedPost;
	}


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return List.of();
	}


	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}


	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}


	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}


	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

    
}
