package coddingcoggies.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import coddingcoggies.dto.SpecialDate;
@Mapper
public interface SpecialDateMapper {


	List<SpecialDate> getAllSpecialDateByMemberNo(int member_no);
	
	/*	 void insertSpecialDate(String specialDate_date, 
			int specialDate_type, String specialDate_color, 
			String specialDate_name, int member_no);
	*/ 
	void insertSpecialDate(SpecialDate specialDate);
	
	SpecialDate getSpecialDateById(int specialDate_id);

	void updateSpecialDate(SpecialDate specialDate);
	
	void deleteSpecialDate(int specialDate_id);
}


