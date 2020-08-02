package com.promineotech.mediaStreamingApi.repository;

import org.springframework.data.repository.CrudRepository;

import com.promineotech.mediaStreamingApi.entity.Movie;

public interface MovieRepository extends CrudRepository<Movie, Long>{

}
