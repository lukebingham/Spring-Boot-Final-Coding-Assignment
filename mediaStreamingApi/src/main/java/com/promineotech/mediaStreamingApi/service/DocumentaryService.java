package com.promineotech.mediaStreamingApi.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.promineotech.mediaStreamingApi.entity.Documentary;
import com.promineotech.mediaStreamingApi.repository.DocumentaryRepository;

@Service
public class DocumentaryService {
	
	private static final Logger logger = LogManager.getLogger(DocumentaryService.class);
	
	@Autowired
	private DocumentaryRepository repo;
	
	public Documentary addDocumentary(Documentary documentary) {
		return repo.save(documentary);
	}
	
	public Iterable<Documentary> getDocumentaries() {
		return repo.findAll();
	}
	
	public Documentary getDocumentaryById(Long id) throws Exception {
		try {
			return repo.findOne(id);
		} catch (Exception e) {
			logger.error("Exception occured while trying to find documentary: " + id, e);
			throw new Exception("Unable to locate documentary!");
		}
	}
	
	public Documentary updateDocumentary(Documentary documentary, Long id) throws Exception {
		try {
			Documentary oldDocumentary = repo.findOne(id);
			oldDocumentary.setSubject(documentary.getSubject());
			oldDocumentary.setTitle(documentary.getTitle());
			oldDocumentary.setRating(documentary.getRating());
			return repo.save(oldDocumentary);
		} catch (Exception e) {
			logger.error("Exception occured while trying to update documentary: " + id, e);
			throw new Exception("Unable to update documentary!");
		}
	}
	
	public void removeDocumentary(Long id) throws Exception {
		try {
			repo.delete(id);
		} catch (Exception e) {
			logger.error("Exception occured while trying to delete documentary: " + id, e);
			throw new Exception("Unable to delete documentary!");
		}
	}
}
