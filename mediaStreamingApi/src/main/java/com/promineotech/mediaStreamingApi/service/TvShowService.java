package com.promineotech.mediaStreamingApi.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.promineotech.mediaStreamingApi.entity.TvShow;
import com.promineotech.mediaStreamingApi.repository.TvShowRepository;

@Service
public class TvShowService {

	private static final Logger logger = LogManager.getLogger(TvShowService.class);
	
	@Autowired
	private TvShowRepository repo;
	
	public TvShow addTvShow(TvShow tvShow) {
		return repo.save(tvShow);
	}
	
	public Iterable<TvShow> getTvShows() {
		return repo.findAll();
	}
	
	public TvShow getTvShowById(Long id) throws Exception {
		try {
			return repo.findOne(id);
		} catch (Exception e) {
			logger.error("Exception occured while trying to find tv show: " + id, e);
			throw new Exception("Unable to locate tv show!");
		}
	}
	
	public TvShow updateTvShow(TvShow tvShow, Long id) throws Exception {
		try {
			TvShow oldTvShow = repo.findOne(id);
			oldTvShow.setGenre(tvShow.getGenre());
			oldTvShow.setSeasons(tvShow.getSeasons());
			oldTvShow.setTitle(tvShow.getTitle());
			return repo.save(oldTvShow);
		} catch (Exception e) {
			logger.error("Exception occured while trying to update tv show: " + id, e);
			throw new Exception("Unable to update tv show!");
		}
	}
	
	public void removeTvShow(Long id) throws Exception {
		try {
			repo.delete(id);
		} catch (Exception e) {
			logger.error("Exception occured while trying to delete tv show: " + id, e);
			throw new Exception("Unable to delete tv show!");
		}
	}
}
