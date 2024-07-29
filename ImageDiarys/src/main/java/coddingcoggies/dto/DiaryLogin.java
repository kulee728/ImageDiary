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
public class DiaryLogin {
	private int member_no;
	private String member_id;
	private String member_pw;
	private String member_diaryname;
	private String member_email;
}
