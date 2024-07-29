package coddingcoggies.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Diary {
	private int diary_id;
	private String diary_title;
	private int diary_feelingCode; //1. 좋음 2.보통 3.슬픔 4.화남
	private String diary_date; //8글자 date : yyyyMMdd
	private String diary_contents;
	private int diary_weatherCode; //1.맑음 2. 흐림 3.비 4.눈
	private int member_no;
	private String diary_fileurl; //1개 이상일때(파일 경로 묶음) , 로 구분
}
