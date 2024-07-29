package coddingcoggies.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import coddingcoggies.dto.Diary;
import coddingcoggies.dto.SpecialDate;
import coddingcoggies.mapper.MainPageMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MainPageService {

	@Autowired
	private MainPageMapper mainPageMapper;
	
	public List<SpecialDate> getAllSpecialDateByMemberNo(int member_no, String yyyyMM, String todayDate){
		log.info("serivce string : "+ mainPageMapper.getAllSpecialDateByMemberNo(member_no,yyyyMM,todayDate).toString());
		return mainPageMapper.getAllSpecialDateByMemberNo(member_no,yyyyMM,todayDate);
	}

	public List <Diary> getAllDiaryByMemberNo(int member_no){
		return mainPageMapper.getAllDiaryByMemberNo(member_no);
	}
	
}
