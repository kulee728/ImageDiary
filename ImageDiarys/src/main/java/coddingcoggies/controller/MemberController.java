package coddingcoggies.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import coddingcoggies.dto.DiaryLogin;
import coddingcoggies.dto.Member;
import coddingcoggies.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MemberController {

	@Autowired
	private MemberService memberService;

	@GetMapping("/signUp")
	public String registerForm(Model model) {
		model.addAttribute("newMem", new Member());
		return "signUp";
	}

	@PostMapping("/register")
	public String signUpComplete(Member member, Model model) {
		memberService.insertMember(member);
		model.addAttribute("msg", "회원가입 완료!");
		return "redirect:/";
	}

	@GetMapping("/idCheck")
	@ResponseBody // json type
//	public boolean getId(Model model, @RequestBody String member_id) {
	public Map<String, Object> getId(@RequestParam String member_id) {
		// 만약에 셀렉트 했을 때 조회되면 이미 존재하는 아이디입니다, 뜨게 만들어주기
		Map<String, Object> response = new HashMap<String, Object>();
		//return !memberService.getId(member_id);
		boolean isCount = memberService.getId(member_id);
		//System.out.println("Controller - member_id : " + member_id  + " , is Count : " + isCount);
		
		response.put("isCount", isCount);
		return response;
	}
}