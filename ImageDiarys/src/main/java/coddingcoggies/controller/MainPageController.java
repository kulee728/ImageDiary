package coddingcoggies.controller;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;
import static java.time.temporal.ChronoUnit.DAYS;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import coddingcoggies.dto.Diary;
import coddingcoggies.dto.DiaryLogin;
import coddingcoggies.dto.SpecialDate;
import coddingcoggies.object.CalanderDay;
import coddingcoggies.service.MainPageService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MainPageController {

	@Autowired
	private MainPageService mainPageService;
	
	public static String todayYYYYMMDD;

	@PostMapping("/main/{yyyyMM}")
	public String changeMainContents(@PathVariable("yyyyMM") String yyyyMM,Model model, HttpSession session) {
		DiaryLogin diaryLogin = (DiaryLogin)session.getAttribute("loginSession");
		if(diaryLogin==null) {
			return "redirect:/";
		}
		int year = Integer.valueOf(yyyyMM.substring(0,4));
		int month = Integer.valueOf(yyyyMM.substring(4));

		mainCalandarDayDrawer(model,session,year,month,diaryLogin);
		mainPageGetAllSpecialDate(model,diaryLogin.getMember_no(),year+String.format("%02d",month),todayYYYYMMDD); //기념일 목록 뽑아오기
		return "/diaryMain";
	}
	public String yyyyMMtoPrev(String yyyyMM) {
		
		int year = Integer.valueOf(yyyyMM.substring(0,4));
		int month = Integer.valueOf(yyyyMM.substring(4));
		if(month==1) {
			year--;
			month=13;
		}
		return year+String.format("%02d",month-1);
	}
	
	public String yyyyMMtoNext(String yyyyMM) {
		
		int year = Integer.valueOf(yyyyMM.substring(0,4));
		int month = Integer.valueOf(yyyyMM.substring(4));
		if(month==12) {
			year++;
			month=0;
		}
		return year+String.format("%02d",month+1);
	}
	
	
	@GetMapping("/diaryMain")
	public String showInitMainContents(Model model, HttpSession session) {
		//getAllSpecialDate(model);
		
		DiaryLogin diaryLogin = (DiaryLogin)session.getAttribute("loginSession");
		if(diaryLogin==null) {
			return "redirect:/";
		}
		log.info("여기는처음메인이야");
		Calendar cal = Calendar.getInstance(); 
		int year = cal.get(Calendar.YEAR); 
		int month = cal.get(Calendar.MONTH) + 1; 
		setTodayInfo(model);
		/*int day = cal.get(Calendar.DAY_OF_MONTH);
		model.addAttribute("yyyyMM",year+String.format("%02d",month));
		//헤더
		model.addAttribute("todayInfo",
				year+"-"+String.format("%02d",month)+"-"+String.format("%02d",day));
		*/
		//헤더 끝

		//달력 그리기

		mainCalandarDayDrawer(model,session,year,month,diaryLogin);//이달의 날짜,model,session 넘기고 조회
		mainPageGetAllSpecialDate(model,diaryLogin.getMember_no(),year+String.format("%02d",month),todayYYYYMMDD);
		log.info("prev to yyyyMM is "+String.valueOf(model.getAttribute("yyyyMM")));
		//log.info("yyyyMM is "+String.valueOf(model.getAttribute("yyyyMM")));
		//달력 그리기 끝
		return "diaryMain";
	}

	private void mainCalandarDayDrawer(Model model, HttpSession session, int year, int month
			, DiaryLogin diaryLogin) {
		
		//Path directoryPath = Paths.get("src", "main", "resources","static", "img", "user", String.valueOf(user_id));
		
		//log.info("프로젝트경로2 :"+absFilePath);
		
		
		setTodayInfo(model);
		//member_no에 일치하는 diaryList, specialDateList 가져오기
		List<Diary> diaryList = mainPageService.getAllDiaryByMemberNo(diaryLogin.getMember_no());
		List<SpecialDate> specialDateList = mainPageService.getAllSpecialDateByMemberNo(diaryLogin.getMember_no(),year+String.format("%02d",month),todayYYYYMMDD);
		
		String todayHeader = year+"년 "+month+"월";
		model.addAttribute("todayHeader",todayHeader);
	
		
		Calendar cal = Calendar.getInstance(); //이번달 첫 날 구하기
		//cal.set(Calendar.YEAR, year);
		//cal.set(Calendar.MONTH, month);  //추후 날짜가 바뀔 것을 대비..
		//log.info("xxx\ncalandar year,month "+year+""+month+cal.toString()+"xxx\n");
		//cal.set(Calendar.DAY_OF_MONTH,1);
		cal.set(year, month-1, 1);
		CalanderDay [] days = new CalanderDay[42]; //나중에 사이즈로 바꿔주자
		int dayNum=1; //1부터 최대 31까지 할당되는 값
		
		//cal.set(Calendar.DAY_OF_MONTH,1); //DAY_OF_MONTH를 1로 설정 (월의 첫날)
		int dateStartPos = cal.get(Calendar.DAY_OF_WEEK); //그 주의 요일 반환 (일:1 ~ 토:7)
		log.info("dateStartPos : "+dateStartPos);
		log.info("getActualMaximum : "+cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		String [] dayNameList = {"일","월","화","수","목","금","토"};
		model.addAttribute("dayNameList",dayNameList);
		
		for(int i=0;i<days.length;i++) {
			days[i] = new CalanderDay();
			//첫 날의 요일위치.토요일은 7이다. log.info("dateStartPos : "+dateStartPos);
			if(i>=dateStartPos-1 && dayNum<=cal.getActualMaximum(Calendar.DAY_OF_MONTH)) { //0이면 일요일부터 시작하는 달, days[0]일요일 days[6]금요일 고정
				String yyyyMMdd = String.valueOf(year) 
						+ String.format("%02d",month)+String.format("%02d",dayNum);
				days[i].setYyyyMMdd(yyyyMMdd);
				for(Diary d : diaryList) {
					if((d.getDiary_date()).equals(yyyyMMdd)) {
						days[i].setDiaryYN(true);
						days[i].setDiary_id(d.getDiary_id());
						//log.info("다이어리 true : "+d.toString());
						//log.info("days true : "+days[i].toString());
						 //log.info("*****diaryYN  date: " + yyyyMMdd);
						break;
					}
				}
				for(SpecialDate sd : specialDateList) {
					if((sd.getSpecialDate_date()).equals(yyyyMMdd)) {
						days[i].setSpecialDateYN(true);
						days[i].setSpecialDate_id(sd.getSpecialDate_id());
						break;
					}
				}				
				//20240623 등 날짜에 대한 다이어리,기념일을 검색한다.
				days[i].setDayNum(Integer.toString(dayNum++));
			}
		}
		//첫날의 요일과 해당 달의 날수를 계산한다.
		//첫날의 요일에 해당되는 index
		log.info("yyyy + MM is "+year+String.format("%02d",month));
		log.info("yyyyMM is "+String.valueOf(model.getAttribute("yyyyMM")));
		model.addAttribute("yyyyMM",year+String.format("%02d",month));
		model.addAttribute("yyyyMMprev",yyyyMMtoPrev(year+String.format("%02d",month)));
		model.addAttribute("yyyyMMnext",yyyyMMtoNext(year+String.format("%02d",month)));
		model.addAttribute("mainCalendardays",days);
		
		//기념일 목록 뽑아오기
	}


	/*
	 기념일 영역
	 1) 기념일 select * from specialDate > 목록 표시
	 2) 기념일.displayText 에 대한 값 계산 (D-95, 혹은 2015-07-30)
	 */


	private void mainPageGetAllSpecialDate(Model model,int member_no,String yyyyMM, String todayDate) {
		List<SpecialDate> specialDateList = mainPageService.getAllSpecialDateByMemberNo(member_no,yyyyMM,todayDate);
		
		for(SpecialDate sd : specialDateList) {
			if(sd.getSpecialDate_type()==1){{
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
				LocalDate dDay_date = LocalDate.parse(sd.getSpecialDate_date(), formatter);
				LocalDate today_date = LocalDate.now();
				sd.setDateDisplayText("D"+DAYS.between(dDay_date, today_date));
			}	
			}
			else if(sd.getSpecialDate_type()==2) {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
				sd.setDateDisplayText(LocalDate.parse(sd.getSpecialDate_date(), formatter).toString());
			}
		}
		
		model.addAttribute("specialDateList",specialDateList);
	}
	
	
	private void setTodayInfo(Model model) {
		Calendar cal = Calendar.getInstance(); 
		int year = cal.get(Calendar.YEAR); 
		int month = cal.get(Calendar.MONTH) + 1; 
		int day = cal.get(Calendar.DAY_OF_MONTH);
		todayYYYYMMDD = year+String.format("%02d",month)+String.format("%02d",day);
		model.addAttribute("todayYYYYMM",year+String.format("%02d",month));
		//헤더
		model.addAttribute("todayInfo",
				year+"-"+String.format("%02d",month)+"-"+String.format("%02d",day));
	}
	
	
	
}
