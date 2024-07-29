package coddingcoggies.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import coddingcoggies.service.EmailService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MailController {
	
	@Autowired
	private final EmailService mailService;
	
	@ResponseBody
	@PostMapping("/mail")
	public String mailSend(String mail) {
		int number = mailService.sendMail(mail);
		String num = "" + number;
		return num;
	}
	
}
