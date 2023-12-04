package kr.or.ddit.controller.crud.notice;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/notice")
public class NoticeLoginController {

	@RequestMapping(value="/login.do", method = RequestMethod.GET)
	public String noticeLogin(Model model) {
		model.addAttribute("bodyText", "login-page");
		return "conn/login";
	}
	
	@RequestMapping(value="/signup.do", method = RequestMethod.GET)
	public String signupForm(Model model) {
		model.addAttribute("bodyText", "register-page");
		return "conn/register";
	}
}
