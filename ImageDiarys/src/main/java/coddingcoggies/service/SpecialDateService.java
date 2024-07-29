package coddingcoggies.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import coddingcoggies.dto.SpecialDate;
import coddingcoggies.mapper.SpecialDateMapper;

@Service
public class SpecialDateService {
	@Autowired
	private SpecialDateMapper specialDateMapper;

	public void insertSpecialDate(SpecialDate specialDate) {
		
		specialDateMapper.insertSpecialDate(specialDate);
	}

	public List<SpecialDate> getAllSpecialDateByMemberNo(int member_id) {
		
		return specialDateMapper.getAllSpecialDateByMemberNo(member_id);
	}
	
	public SpecialDate getSpecialDateById(int specialDate_id) {
		return specialDateMapper.getSpecialDateById(specialDate_id);
	}
	
	public void updateSpecialDate(SpecialDate specialDate) {
	
		specialDateMapper.updateSpecialDate(specialDate);
	}
	public void deleteSpecialDate(int specialDate_id) {
		specialDateMapper.deleteSpecialDate(specialDate_id);
	}
}
