package coddingcoggies.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import coddingcoggies.dto.Diary;

@Mapper
@Repository
public interface DiaryMapper {

	/*
	Diary getDiary(@Param("diary_title") String diary_title,
				   @Param("diary_feelingCode") int diary_feelingCode,
				   @Param("diary_weaterCode") int diary_weaterCode,
				   @Param("diary_contents") String diary_contents,
				   @Param("diary_fileurl") String diary_fileurl);
	*/
	void insertDiary(Diary diary);
	void updateDiary(Diary diary);
	void deleteDiary(@Param("diary_id") int diary_id);

	List<Diary> getAllDiary();
	Diary getDiaryById(@Param("diary_id") int diary_id);
	
	
}
