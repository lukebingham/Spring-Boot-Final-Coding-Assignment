package com.promineotech.mediaStreamingApi.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.JoinColumn;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Documentary {
	
	private Long id;
	private String subject;
	private String title;
	private String rating;
	
	@JsonIgnore
	private Set<Playlist> playlists;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getSubject() {
		return subject;
	}
	
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getRating() {
		return rating;
	}
	
	public void setRating(String rating) {
		this.rating = rating;
	}

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "documentaryPlaylist",
			joinColumns = @JoinColumn(name = "playlistId", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "documentaryId", referencedColumnName = "id"))
	public Set<Playlist> getPlaylists() {
		return playlists;
	}

	public void setPlaylists(Set<Playlist> playlists) {
		this.playlists = playlists;
	}
}
