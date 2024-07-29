package coddingcoggies.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import coddingcoggies.dto.DiaryLogin;

@Mapper
public interface DiaryLoginMapper {
	DiaryLogin getLogin(@Param("member_id") String member_id,
						  @Param("member_pw") String member_pw);
}