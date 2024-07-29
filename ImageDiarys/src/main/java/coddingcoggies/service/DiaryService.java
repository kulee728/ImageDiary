package coddingcoggies.service;

import java.io.File;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import coddingcoggies.dto.Diary;
import coddingcoggies.mapper.DiaryMapper;
import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j
public class DiaryService {
	
	@Autowired
	private DiaryMapper diaryMapper;
	
	/*
	public Diary getDiary(String diary_title, int diary_feelingCode,
				   		  int diary_weaterCode, String diary_contents, 
				   		  String diary_fileurl) {
		return diaryMapper.getDiary(diary_title, diary_feelingCode, diary_weaterCode, diary_contents, diary_fileurl);
	}
	*/
	
	//일기 작성
	
	public void insertDiary(Diary diary) {
		diaryMapper.insertDiary(diary);	
	}
	
	//String today, int member_no, ++
	public void insertDiary(String today, int member_no, String diary_title, String diary_contents, int diary_feelingCode, int diary_weatherCode, MultipartFile file) {
		
		String originFilename = file.getOriginalFilename(); // origin file name
		
		String absFilePath = (new File("")).getAbsolutePath();
		absFilePath = absFilePath.replaceAll("//","/");
		absFilePath += "/src/main/resources/static";
		
		String file_saveDir = "/userImage/" + member_no + "/" + today;
		String uploadDir = absFilePath+ file_saveDir;
		 // static/userImage/1/20240702_1 식으로 붙는다. 이때 1은 유저의 member_no
		File imgFolder = new File(uploadDir);
		File imgFile = new File(imgFolder + "/" + originFilename);
		
		if(!imgFolder.exists()) {
			imgFolder.mkdirs(); //not exists folder -> make folders
		}
		try {
		file.transferTo(imgFile);
		
		Diary diary = new Diary();
		diary.setDiary_date(today); // ++
		diary.setMember_no(member_no); // ++
		diary.setDiary_title(diary_title);
		diary.setDiary_contents(diary_contents);
		diary.setDiary_feelingCode(diary_feelingCode);
		diary.setDiary_weatherCode(diary_weatherCode);
		diary.setDiary_fileurl(file_saveDir+ "/" + originFilename);
		
		diaryMapper.insertDiary(diary);
		log.info(diary.toString());
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	//일기 수정
	/*
	public void updateDiary(Diary diary) {
		diaryMapper.updateDiary(diary);
	}*/
	
	public void updateDiary(int diary_id, String today, int member_no, String diary_title, String diary_contents, int diary_feelingCode, int diary_weatherCode, MultipartFile file,String original_fileurl) {
	    String originFilename = file.getOriginalFilename();
	        String absFilePath = (new File("")).getAbsolutePath().replaceAll("//","/");
	        absFilePath += "/src/main/resources/static";
	        
	        String file_saveDir = "/userImage/" + member_no + "/" + today;
	        String uploadDir = absFilePath + file_saveDir;
	        File imgFolder = new File(uploadDir);
	        File imgFile = new File(imgFolder, originFilename);
	        
	        if (!imgFolder.exists()) {
	            imgFolder.mkdirs();
	        }
	        Diary diary = new Diary();
	        try {
	            file.transferTo(imgFile);
	            diary.setDiary_fileurl(file_saveDir + "/" + originFilename);

	        } catch (Exception e) {
	            e.printStackTrace();
	            diary.setDiary_fileurl(original_fileurl);
	        }
           
            diary.setDiary_id(diary_id);
            diary.setDiary_date(today);
            diary.setMember_no(member_no);
            diary.setDiary_title(diary_title);
            diary.setDiary_contents(diary_contents);
            diary.setDiary_feelingCode(diary_feelingCode);
            diary.setDiary_weatherCode(diary_weatherCode);
            
            
            diaryMapper.updateDiary(diary);
	}
	
	//일기 삭제
	public void deleteDiary(int diary_id) {
		diaryMapper.deleteDiary(diary_id);
	}
	
	/*
	public List<Diary> getAllDiary(){
		return diaryMapper.getAllDiary();
	}
	*/
	
	public List<Diary> getAllDiary(int diary_id){
		return diaryMapper.getAllDiary();
	}
	
	public Diary getDiaryById(int diary_id) {
		return diaryMapper.getDiaryById(diary_id); // DiaryMapper를 통해 특정 다이어리 조회
	}

}