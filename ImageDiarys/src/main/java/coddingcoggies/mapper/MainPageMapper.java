package coddingcoggies.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import coddingcoggies.dto.Diary;
import coddingcoggies.dto.SpecialDate;

@Mapper
public interface MainPageMapper {

	List<SpecialDate> getAllSpecialDateByMemberNo(@Param("member_no") int member_no,
			@Param("yyyyMM") String yyyyMM,
			@Param("todayDate") String todayDate
			);
	List <Diary> getAllDiaryByMemberNo(int member_no);
}
