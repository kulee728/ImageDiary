package coddingcoggies.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Member {
	private int member_no;
	private String member_id;
	private String member_pw;
	private String member_diaryName;
	private String member_email;
}
