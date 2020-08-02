package com.promineotech.mediaStreamingApi.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.promineotech.mediaStreamingApi.service.PlaylistService;

@RestController
@RequestMapping("members/{id}/playlists")
public class PlaylistController {
	
	@Autowired
	private PlaylistService service;
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Object> createMember(@RequestBody Set<Long> movieIds, @PathVariable Long id) {
		try {
			return new ResponseEntity<Object>(service.submitNewMoviePlaylist(movieIds, id), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Object> getPlaylists() {
		return new ResponseEntity<Object>(service.getPlaylists(), HttpStatus.OK);
	}
	
}
