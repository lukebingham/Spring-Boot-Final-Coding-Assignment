package com.promineotech.mediaStreamingApi.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.promineotech.mediaStreamingApi.entity.Movie;
import com.promineotech.mediaStreamingApi.repository.MovieRepository;

@Service
public class MovieService {
	
	private static final Logger logger = LogManager.getLogger(MovieService.class);

	@Autowired
	private MovieRepository repo;
	
	public Movie addMovie(Movie movie) {
		return repo.save(movie);
	}
	
	public Iterable<Movie> getMovies() {
		return repo.findAll();
	}
	
	public Movie getMovieById(Long id) throws Exception {
		try {
			return repo.findOne(id);
		} catch (Exception e) {
			logger.error("Exception occured while trying to find movie: " + id, e);
			throw new Exception("Unable to locate movie!");
		}
	}
	
	public Movie updateMovie(Movie movie, Long id) throws Exception {
		try {
		Movie oldMovie = repo.findOne(id);
		oldMovie.setTitle(movie.getTitle());
		oldMovie.setGenre(movie.getGenre());
		oldMovie.setRuntime(movie.getRuntime());
		oldMovie.setRating(movie.getRating());
		return repo.save(oldMovie);
	} catch (Exception e) {
		logger.error("Exception occured while trying to update movie: " + id, e);
		throw new Exception("Unable to update movie!");
		
		}
	}
	
	public void removeMovie(Long id) throws Exception {
		try {
			repo.delete(id);
		} catch (Exception e) {
			logger.error("Exception occured while trying to delete movie: " + id, e);
			throw new Exception("Unable to delete movie!");
		}
	}
}
