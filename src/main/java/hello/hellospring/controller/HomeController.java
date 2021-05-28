package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	// 처음에 index페이지를 만들어 뒀는데 home.html로 가는 이유 : 스프링 안에서 매핑된 거 먼저 찾고 다음에 정적 html 찾음
	@GetMapping("/")
	public String home() {
		return "home";
	}
}
