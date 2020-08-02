package com.promineotech.mediaStreamingApi.service;

import java.util.HashSet;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.promineotech.mediaStreamingApi.entity.Member;
import com.promineotech.mediaStreamingApi.entity.Movie;
import com.promineotech.mediaStreamingApi.entity.Playlist;
import com.promineotech.mediaStreamingApi.repository.MemberRepository;
import com.promineotech.mediaStreamingApi.repository.MovieRepository;
import com.promineotech.mediaStreamingApi.repository.PlaylistRepository;

@Service
public class PlaylistService {

	private static final Logger logger = LogManager.getLogger(PlaylistService.class);
	
	@Autowired
	private PlaylistRepository repo;
	
	@Autowired
	private MemberRepository memberRepo;
	
	@Autowired
	private MovieRepository movieRepo;
	
	public Playlist submitNewMoviePlaylist(Set<Long> movieIds, Long memberId) throws Exception {
		try {
			Member member = memberRepo.findOne(memberId);
			Playlist playlist = initializeNewMoviePlaylist(movieIds, member);
			return repo.save(playlist);
		} catch (Exception e) {
			logger.error("Exception occurred while trying to create new movie playlist for member: " + memberId, e);
			throw e;
		}
	}
	
	private Playlist initializeNewMoviePlaylist(Set<Long> movieIds, Member member) {
		Playlist playlist = new Playlist();
		playlist.setMovies(convertToMovieSet(movieRepo.findAll(movieIds)));
		playlist.setMember(member);
		addPlaylistToMovies(playlist);
		return playlist;
	}
	
	private void addPlaylistToMovies(Playlist playlist) {
		Set<Movie> movies = playlist.getMovies();
		for (Movie movie: movies) {
			movie.getPlaylists().add(playlist);
		}
	}
	
	private Set<Movie> convertToMovieSet(Iterable<Movie> iterable) {
		Set<Movie> set = new HashSet<Movie>();
		for (Movie movie : iterable) {
			set.add(movie);
		}
		return set;
	}
	
	public Iterable<Playlist> getPlaylists() {
		return repo.findAll();
	}
	
	
}	