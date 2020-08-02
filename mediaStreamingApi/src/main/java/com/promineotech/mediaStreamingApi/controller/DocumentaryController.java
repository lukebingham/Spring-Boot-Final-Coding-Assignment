package com.promineotech.mediaStreamingApi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.promineotech.mediaStreamingApi.entity.Documentary;
import com.promineotech.mediaStreamingApi.service.DocumentaryService;

@RestController
@RequestMapping("/documentaries")
public class DocumentaryController {
	
	@Autowired
	private DocumentaryService service;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Object> getDocumentaries() {
		return new ResponseEntity<Object>(service.getDocumentaries(), HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Object> addDocumentary(@RequestBody Documentary documentary) {
		return new ResponseEntity<Object>(service.addDocumentary(documentary), HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Object> updateDocumentary(@RequestBody Documentary documentary, @PathVariable Long id) {
		try {
			return new ResponseEntity<Object>(service.updateDocumentary(documentary, id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>("Unable to update documentary!", HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Object> deleteDocumentary(@PathVariable Long id) {
		try {
			service.removeDocumentary(id);
			return new ResponseEntity<Object>("Successfully deleted documentary with id: " + id, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>("Not able to delete documentary!", HttpStatus.BAD_REQUEST);
		}
	}

}
