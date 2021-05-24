package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

	@GetMapping("hello")
	public String hello(Model model) {
		model.addAttribute("data", "spring!!");
		return "hello";
	}
	
	// *빌드 및 실행 cmd명령어(윈도우) : gradlew.bat build (이러면 build\libs에 jar파일 생성됨)
}
