package com.promineotech.mediaStreamingApi.repository;

import org.springframework.data.repository.CrudRepository;

import com.promineotech.mediaStreamingApi.entity.Playlist;

public interface PlaylistRepository extends CrudRepository<Playlist, Long> {

}
