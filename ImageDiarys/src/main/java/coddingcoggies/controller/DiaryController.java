package coddingcoggies.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import coddingcoggies.dto.Diary;
import coddingcoggies.dto.DiaryLogin;
import coddingcoggies.service.DiaryService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/diary")
public class DiaryController {
	
	public static String cur_date;
	public static String original_fileurl;
	//public static String cur_member_no;
	
	
	@Autowired
	private DiaryService diaryService;
	
	/* ************************** 해당 날짜 받아오기->왜 안되니 또?******************************** */


	@GetMapping("/diaryView/{diary_id}/{today}")
    public String viewDiary(@PathVariable int diary_id, @PathVariable String today, Model model,HttpSession session) {
		DiaryLogin diaryLogin = (DiaryLogin)session.getAttribute("loginSession");
		if(diaryLogin==null) {
			return "redirect:/";
		} //로그인 세션 확인
        model.addAttribute("diary_id", diary_id);
        model.addAttribute("today", today);
        return "diary/diaryView"; // exam
    }

	//++ String member_no -> int member_no
    @GetMapping("/diaryWrite/{member_no}/{today}")
    public String writeDiary(@PathVariable int member_no, @PathVariable String today, Model model, HttpSession session) {
    	//log.info("today :" +today);
    	
		DiaryLogin diaryLogin = (DiaryLogin)session.getAttribute("loginSession");
		if(diaryLogin==null || diaryLogin.getMember_no() != member_no) {
			return "redirect:/";
		} //로그인 세션 확인
    	
    	model.addAttribute("diary", new Diary());
    	model.addAttribute("member_no", member_no);
    	model.addAttribute("today", today);
      cur_date = today;
        
        return "diary/diaryWrite";
    }
    /* ******************************************************************************** */
    
	@GetMapping("/diaryWrite")
	public String toDiaryWrite(Model model) {
		model.addAttribute("diary", new Diary()); // get = 가져오다. 다이어리 객체에 작성된 빈 공간을
		return "diary/diaryWrite";
	}

	/*
	 * @PostMapping("/diaryWrite") //diary/diaryWrite public String
	 * insertDiary(Diary diary, Model model) { log.info(diary.toString());
	 * diaryService.insertDiary(diary); model.addAttribute("msg",
	 * "오늘의 일기가 등록되었습니다."); return "diaryView"; }
	 */
	
	// ++ @RequestParam("diary_date") String diary_date, @RequestParam("member_no") int member_no
	@PostMapping("/diaryWrite")
	public String insertDiary(@RequestParam("diary_title") String diary_title,
			@RequestParam("diary_contents") String diary_contents, @RequestParam("feelingCode") int diary_feelingCode,
			@RequestParam("weatherCode") int diary_weatherCode, @RequestParam("diary_fileurl") MultipartFile file, HttpSession session) {
		log.info("일기 작성 날짜 : "+cur_date);
		
		DiaryLogin diaryLogin = (DiaryLogin)session.getAttribute("loginSession");
		if(diaryLogin==null) {
			return "redirect:/";
		}
		log.info("타이틀" +diary_title);
		
		String diary_date = cur_date;
		int member_no = diaryLogin.getMember_no();
				
		// ++ diary_date, member_no,
		diaryService.insertDiary(diary_date, member_no, diary_title, diary_contents, diary_feelingCode, diary_weatherCode, file);
		
		return "redirect:/diaryMain";
	}

	/*
	 * @GetMapping("/diaryView/{diary_id}") public String getAllDiary(Model model) {
	 * List<Diary> diaryView = diaryService.getAllDiary();
	 * model.addAttribute("diaryView", diaryView); // List<Diary>를 추가 return
	 * "diaryView"; }
	 */
	/********************
	 * http://localhost:9099/diary/diaryView/null
	 *************************************************************************/
	@GetMapping("/diaryView/{diary_id}")
	public String getDiaryById(@PathVariable("diary_id") int diary_id, Model model, HttpSession session) {
		
		DiaryLogin diaryLogin = (DiaryLogin)session.getAttribute("loginSession");
		if(diaryLogin==null) {
			return "redirect:/";
		} //로그인 세션 확인
		
		
		System.out.println(" **** id ****" + diary_id);

		Diary diary = diaryService.getDiaryById(diary_id);
		if(diary.getMember_no() != diaryLogin.getMember_no()) {
			
		}
		
		//log.info("=== diary === : " + diary);
		if (diary != null) {
			if(diaryLogin.getMember_no() != diary.getMember_no()) {
				log.info("다이어리 주인 :"+diary.getMember_no());
				log.info("로그인 주인 :"+diaryLogin.getMember_no());
				return "redirect:/diaryMain";
			} //만약 사용자가 다른 사용자의 다이어리에 접근하지 않도록 세션과 비교(id로만 접근하기 때문에)
			cur_date = diary.getDiary_date();
			model.addAttribute("today", cur_date);
			
			model.addAttribute("diary", diary);
			return "diary/diaryView"; // 조회 페이지로 이동
		} 
		
		return "redirect:/diary/diaryWrite"; // 작성 페이지로 이동

	}
	@GetMapping("/diaryUpdate/{diary_id}")
	public String updateDiary(@PathVariable("diary_id") int diary_id, Model model) {
		Diary diary = diaryService.getDiaryById(diary_id);
		
		log.info(" update diary : " + diary);
		cur_date = diary.getDiary_date();
		original_fileurl = diary.getDiary_fileurl();
		
		//log.info("아아아아아아아아ㅏ아아아아ㅓ");
		model.addAttribute("diary", diary);
		return "diary/diaryUpdate";
	}
	
	@PostMapping("/diaryUpdate/{diary_id}")
	public String updateDiary(
			@PathVariable("diary_id") int diary_id,
			@RequestParam("diary_title") String diary_title,
			@RequestParam("diary_contents") String diary_contents, 
			@RequestParam("feelingCode") int diary_feelingCode,
			@RequestParam("weatherCode") int diary_weatherCode,
			  @RequestParam("update_image_url") MultipartFile file,
			HttpSession session//,파일 어떻게 하지 MultipartFile file 
			) {
		log.info("아아diary title : "+diary_title);
		log.info("가가 diary id : "+diary_id);
		System.out.println("file : " + file);
		DiaryLogin diaryLogin = (DiaryLogin)session.getAttribute("loginSession");
		if(diaryLogin==null) {
			return "redirect:/";
		}
		String diary_date = cur_date;
		
		int member_no = diaryLogin.getMember_no();
		diaryService.updateDiary(diary_id, diary_date, member_no, diary_title, diary_contents, diary_feelingCode, diary_weatherCode, file,original_fileurl);
	    return "redirect:/diaryMain";
	    }
	
	/*
	@GetMapping("/diaryView/{diary_id}")
	public String deleteDiary(diary_id)
	*/
	
	 @GetMapping("/diaryDelete/{diary_id}")
	    public String deleteDiary(@PathVariable("diary_id") int diary_id, HttpSession session) {
	        DiaryLogin diaryLogin = (DiaryLogin) session.getAttribute("loginSession");
	        if (diaryLogin == null) {
	            return "redirect:/";
	        }

	        Diary diary = diaryService.getDiaryById(diary_id);
	        if (diary != null && diaryLogin.getMember_no() == diary.getMember_no()) {
	            diaryService.deleteDiary(diary_id);
	        }

	        return "redirect:/diaryMain";
	    }

}