package com.promineotech.mediaStreamingApi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.promineotech.mediaStreamingApi.entity.Movie;
import com.promineotech.mediaStreamingApi.service.MovieService;

@RestController
@RequestMapping("/movies")
public class MovieController {
	
	@Autowired
	private MovieService service;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Object> getMovies() {
		return new ResponseEntity<Object>(service.getMovies(), HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Object> addMovie(@RequestBody Movie movie) {
		return new ResponseEntity<Object>(service.addMovie(movie), HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Object> updateMovie(@RequestBody Movie movie, @PathVariable Long id) {
		try {
			return new ResponseEntity<Object>(service.updateMovie(movie, id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>("Unable to update movie!", HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Object> deleteMovie(@PathVariable Long id) {
		try {
			service.removeMovie(id);
			return new ResponseEntity<Object>("Successfully deleted movie with id: " + id, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>("Not able to delete movie!", HttpStatus.BAD_REQUEST);
		}
	}
	
}
