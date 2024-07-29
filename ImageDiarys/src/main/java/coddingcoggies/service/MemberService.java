package coddingcoggies.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import coddingcoggies.dto.Member;
import coddingcoggies.mapper.MemberMapper;

@Service
public class MemberService {
	
	@Autowired
	private MemberMapper memberMapper;
	
	public void insertMember(Member member) {
		memberMapper.insertMember(member);
	}
	/*
	public Member getId() { 
		return memberMapper.getId();
	}
	*/
	
	
	public boolean getId(String member_id) {
		Integer count = memberMapper.getId(member_id);
		System.out.println("service : "+count);
		//return count > 0;
		
		return count != null && count == 0;
	}
	
}
