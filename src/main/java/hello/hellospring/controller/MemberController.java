package hello.hellospring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;

@Controller
public class MemberController {
	
	// 1. 컨포넌트 스캔 써서 등록(어노테이션) 2. 직접 빈 등록하기(SpringConfig 파일) --> 컨트롤러는 1이든 2든 Autowired로 연결해야 됨
	// 의존성 주입 Autowired 1.필드 주입(변경하기 어려움) 2.생성자 주입(권장) 3.setter 주입(public으로 노출돼서 변경 위험 있음)
	// --> 의존 관계가 실행 중에 동적으로 변하는 경우는 없기 때문.

	// 컨트롤러에 넣었으니까 스프링이 관리하게 되고 new 할 것 없이 생성자를 연결해서 전에 만들어 둔 걸로 씀.
	private final MemberService memberService;
	
	// Autowired : 스프링 컨테이너에서 가져옴. @Bean으로 등록돼 있지 않으면 작동X
	@Autowired
	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}
	
	// 회원가입폼 get : http 주소창으로 접근. 조회할 때 많이 씀.
	@GetMapping("/members/new") 
	public String createForm() {
		return "members/createMemberForm";
	}
	
	// 회원가입 post : 데이터를 form 등에 넣어서 전달/등록할 때 많이 씀.
	@PostMapping("/members/new")
	public String create(MemberForm form) { // 받을 때 아예 vo로 받음
		Member member = new Member();
		member.setName(form.getName());
		
		System.out.println("member : "+member.getName());
		
		memberService.join(member);
		
		return "redirect:/"; 
	}
	
	// 회원 목록 : 회원 등록을 메모리 안에 하기 때문에 자바 서버를 내리면 데이터가 다 사라짐 --> DB에 저장해야 됨
	@GetMapping("/members")
	public String list(Model model) {
		List<Member> members = memberService.findMembers(); // member 다 가져와줌
		model.addAttribute("members", members);
		
		return "members/memberList";
	}
	
}
