package com.promineotech.mediaStreamingApi.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Playlist {
	
	private Long id;
	private String name;
	
	@JsonIgnore
	private Member member;
	
	@JsonIgnore
	private Set<Movie> movies;
	
	@JsonIgnore
	private Set<TvShow> tvShows;
	
	@JsonIgnore
	private Set<Documentary> documentaries;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@ManyToOne
	@JoinColumn(name = "memberId")
	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	@ManyToMany(mappedBy = "playlists")
	public Set<Movie> getMovies() {
		return movies;
	}

	public void setMovies(Set<Movie> movies) {
		this.movies = movies;
	}

	@ManyToMany(mappedBy = "playlists")
	public Set<Documentary> getDocumentaries() {
		return documentaries;
	}

	public void setDocumentaries(Set<Documentary> documentaries) {
		this.documentaries = documentaries;
	}

	@ManyToMany(mappedBy = "playlists")
	public Set<TvShow> getTvShows() {
		return tvShows;
	}

	public void setTvShows(Set<TvShow> tvShows) {
		this.tvShows = tvShows;
	}
}
