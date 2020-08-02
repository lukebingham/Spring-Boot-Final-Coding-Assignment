package com.promineotech.mediaStreamingApi.repository;

import org.springframework.data.repository.CrudRepository;

import com.promineotech.mediaStreamingApi.entity.Member;

public interface MemberRepository extends CrudRepository<Member, Long>{

}
