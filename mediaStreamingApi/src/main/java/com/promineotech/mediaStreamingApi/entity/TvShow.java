package com.promineotech.mediaStreamingApi.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class TvShow {
	
	private Long id;
	private String genre;
	private int seasons;
	private String title;
	
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
	
	public String getGenre() {
		return genre;
	}
	
	public void setGenre(String genre) {
		this.genre = genre;
	}
	
	public int getSeasons() {
		return seasons;
	}
	
	public void setSeasons(int seasons) {
		this.seasons = seasons;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "tvShowPlaylist",
			joinColumns = @JoinColumn(name = "playlistId", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "tvShowId", referencedColumnName = "id"))
	public Set<Playlist> getPlaylists() {
		return playlists;
	}

	public void setPlaylists(Set<Playlist> playlists) {
		this.playlists = playlists;
	}

}
