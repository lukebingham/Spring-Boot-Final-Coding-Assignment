package com.promineotech.mediaStreamingApi.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.promineotech.mediaStreamingApi.entity.Member;
import com.promineotech.mediaStreamingApi.repository.MemberRepository;

@Service
public class MemberService {
	
	private static final Logger logger = LogManager.getLogger(MemberService.class);
	
	@Autowired
	private MemberRepository repo;
	
	public Member createMember(Member member) {
		return repo.save(member);
	}
	
	public Iterable<Member> getMembers() {
		return repo.findAll();
	}
	
	public Member getMemberById(Long id) throws Exception {
		try {
			return repo.findOne(id);
		} catch (Exception e) {
			logger.error("Exception occured while trying to find member: " + id, e);
			throw new Exception("Unable to locate customer!");
		}
	}
	
	public Member updateMember(Member member, Long id) throws Exception {
		try {
			Member oldMember = repo.findOne(id);
			oldMember.setUsername(member.getUsername());
			oldMember.setFirstName(member.getFirstName());
			oldMember.setLastName(member.getLastName());
			oldMember.setEmail(member.getEmail());
			return repo.save(oldMember);
		} catch (Exception e) {
			logger.error("Exception occured while trying to update member: " + id, e);
			throw new Exception("Unable to update member!");
		}
	}
		
	public void deleteMember(Long id) throws Exception {
		try {
			repo.delete(id);
		} catch (Exception e) {
			logger.error("Exception occured while trying to delete member: " + id, e);
			throw new Exception("Unable to delete member!");
		}
	}
}
