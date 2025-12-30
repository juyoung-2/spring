package org.joonzis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 예: 브라우저에서 http://localhost:8080/ 입력 시 이 메서드가 실행됨.
 */
@Controller
public class HomeController {
	//루트 URL("/")로 들어오는 GET 요청을 처리함.
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String home() {
		return "index";
	}
	
}