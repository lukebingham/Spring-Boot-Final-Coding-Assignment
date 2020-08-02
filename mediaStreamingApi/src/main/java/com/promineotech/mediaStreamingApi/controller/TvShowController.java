package com.promineotech.mediaStreamingApi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.promineotech.mediaStreamingApi.entity.TvShow;
import com.promineotech.mediaStreamingApi.service.TvShowService;

@RestController
@RequestMapping("/tvshows")
public class TvShowController {
	
	@Autowired
	private TvShowService service;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Object> getTvShows() {
		return new ResponseEntity<Object>(service.getTvShows(), HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Object> addTvShow(@RequestBody TvShow tvShow) {
		return new ResponseEntity<Object>(service.addTvShow(tvShow), HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Object> updateTvShow(@RequestBody TvShow tvShow, @PathVariable Long id) {
		try {
			return new ResponseEntity<Object>(service.updateTvShow(tvShow, id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>("Unable to update tv show!", HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Object> removeTvShow(@PathVariable Long id) {
		try {
			service.removeTvShow(id);
			return new ResponseEntity<Object>("Successfully deleted tv show with id: " + id, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>("Not able to delete tv show!", HttpStatus.BAD_REQUEST);
		}
	}
}
