package coddingcoggies.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import coddingcoggies.dto.DiaryLogin;
import coddingcoggies.dto.SpecialDate;
import coddingcoggies.mapper.MainPageMapper;
import coddingcoggies.service.SpecialDateService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class SpecialDateController {
	
	@Autowired
	private SpecialDateService specialDateService;
	public static int updateTargetId;
	
	@GetMapping("/insertSpecialDate")
	public String specialDate(Model model, HttpSession session) {
		DiaryLogin diaryLogin = (DiaryLogin)session.getAttribute("loginSession");
		if(diaryLogin==null) {
			return "redirect:/";
		}
		model.addAttribute("specialDate",new SpecialDate());
		return "specialDate";
	}
	
	@PostMapping("/insertSpecialDate")
	public String specialDateSave(Model model, 
			@RequestParam("specialDate_date") String specialDate_date,
			@RequestParam("specialDate_type") int specialDate_type,
			@RequestParam("specialDate_name") String specialDate_name,
			HttpSession session) {
		//int specialDate_type = 1;
		
		specialDate_date = specialDate_date.replaceAll("-","");
		
		log.info("건휘야 기념일만들자~ : 타입은 "+specialDate_type);
		
		DiaryLogin diaryLogin = (DiaryLogin)session.getAttribute("loginSession");
		if(diaryLogin==null) {
			return "redirect:/";
		}
		int member_no = diaryLogin.getMember_no();
		
		String [] randomColors = {"#FBD5D5", "#DFBCA9", "#B6A884", "#83956E",
					"#498168", "#006B6C", "#C6E998", "#FFCF90", "#E9985D", "#AE652C"};
		
		String specialDate_color = randomColors[(int)(Math.random()*10)];
		
		SpecialDate specialDate = new SpecialDate();
		specialDate.setSpecialDate_date(specialDate_date);
		specialDate.setSpecialDate_type(specialDate_type);
		specialDate.setSpecialDate_color(specialDate_color);
		specialDate.setSpecialDate_name(specialDate_name);
		specialDate.setMember_no(member_no);
		
		log.info("건휘야 기념일만들자~"+specialDate.toString());
		
		specialDateService.insertSpecialDate(specialDate);
		return "redirect:/diaryMain";
	}
	
	
	
	@GetMapping("/updateSpecialDate")
	public String specialDateUpdate(Model model, HttpSession session) {
		DiaryLogin diaryLogin = (DiaryLogin)session.getAttribute("loginSession");
		int selectedSpecialDate_id = 0;
		if(diaryLogin==null) {
			return "redirect:/";
		}
		//model.addAttribute("selectedSpecialDate_id",selectedSpecialDate_id);

		List<SpecialDate> specialDateList = specialDateService.getAllSpecialDateByMemberNo(diaryLogin.getMember_no());
		model.addAttribute("specialDateList",specialDateList);
		log.info("히히 못가" + specialDateList.toString());
		return "specialDateUpdate";
	}
	
	@PostMapping("/updateSpecialDate")
	public String specialDateUpdateSubmission(Model model, HttpSession session
			, @RequestParam("selectedSpecialDate_id") int specialDate_id) {
		
		updateTargetId = specialDate_id;
		
		SpecialDate specialDate = specialDateService.getSpecialDateById(specialDate_id);
		String dateText = specialDate.getSpecialDate_date();
		dateText = dateText.substring(0,4)+"-"+dateText.substring(4,6)+"-"+dateText.substring(6,8);
		specialDate.setDateDisplayText(dateText);
		model.addAttribute("specialDate",specialDate);
		return "specialDateUpdate2";
	}
	
	@PostMapping("/updateSpecialDate2")
	public String specialDateUpdate2(Model model, HttpSession session
			,@RequestParam("specialDate_date") String specialDate_date,
			@RequestParam("specialDate_type") int specialDate_type,
			@RequestParam("specialDate_name") String specialDate_name) {

			DiaryLogin diaryLogin = (DiaryLogin)session.getAttribute("loginSession");
			if(diaryLogin==null) {
				return "redirect:/";
			}
			int member_no = diaryLogin.getMember_no();
			int specialDateId = updateTargetId;
			
			SpecialDate specialDate = new SpecialDate();
			specialDate_date = specialDate_date.replaceAll("-","");
			
			specialDate.setSpecialDate_date(specialDate_date);
			specialDate.setSpecialDate_type(specialDate_type);
			specialDate.setSpecialDate_name(specialDate_name);
			specialDate.setSpecialDate_id(specialDateId);
			
		specialDateService.updateSpecialDate(specialDate);
		
		return "redirect:/diaryMain";
	}
	
	@PostMapping("/updateSpecialDate2Delete")
	public String specialDateUpdate2(Model model, HttpSession session) {

			DiaryLogin diaryLogin = (DiaryLogin)session.getAttribute("loginSession");
			if(diaryLogin==null) {
				return "redirect:/";
			}
		
			int specialDateId = updateTargetId;
		specialDateService.deleteSpecialDate(specialDateId);
		
		return "redirect:/diaryMain";
	}
	
}

