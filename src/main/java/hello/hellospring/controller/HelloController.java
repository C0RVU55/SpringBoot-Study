package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

	@GetMapping("hello")
	public String hello(Model model) {
		model.addAttribute("data", "spring!!");
		return "hello";
	}

	// *빌드 및 실행 cmd명령어(윈도우) : gradlew.bat build (이러면 build\libs에 jar파일 생성됨)

	// view로 템플릿 엔진 방식으로 렌더링된 html을 보냄
	@GetMapping("hello-mvc")
	public String helloMvc(@RequestParam(value = "name", required = false) String name, Model model) {
		model.addAttribute("name", name);
		return "hello-template";
	}

	@GetMapping("hello-string")
	@ResponseBody // http 바디 부분을 통해 데이터를 직접 전달 (위처럼 html을 보내는 게 아니라 문자열만 보냄)
	public String helloString(@RequestParam("name") String name) {
		return "hello " + name;
	}

	// api방식 --> json형식으로 데이터 전달 (xml은 html처럼 태그 2번 써야 되는데 json은 키:값 형태라 json으로 반환하는 게 추세)
	@GetMapping("hello-api")
	@ResponseBody
	public Hello helloApi(@RequestParam("name") String name) {
		Hello hello = new Hello();
		hello.setName(name);
		return hello;
	}

	// 컨트롤러 안에 스태틱 클래스 만들어서 바로 갖다 쓰기 가능
	static class Hello {
		private String name;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

	}
}
