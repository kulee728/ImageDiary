package coddingcoggies.mapper;

import org.apache.ibatis.annotations.Mapper;

import coddingcoggies.dto.Member;

@Mapper
public interface MemberMapper {
	void insertMember(Member member);
	Integer getId(String member_id);
}