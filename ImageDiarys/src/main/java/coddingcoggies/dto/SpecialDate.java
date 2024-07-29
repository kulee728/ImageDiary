package coddingcoggies.dto;

import lombok.*;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class SpecialDate {
	private int specialDate_id;
	private String specialDate_date;
	private int specialDate_type; // type (1:디데이, 2:지정기념일)
	private String specialDate_color; //색에 대한 value 저장.
	private String specialDate_name;
	private int member_no;
	private String dateDisplayText; // Dday는 D-날짜 , 기념일은 날짜 형식으로


}
