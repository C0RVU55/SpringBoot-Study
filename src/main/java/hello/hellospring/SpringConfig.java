package hello.hellospring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import hello.hellospring.repository.MemberRepository;
import hello.hellospring.service.MemberService;

@Configuration
public class SpringConfig {

	/*
	private DataSource dataSource;

	@Autowired
	public SpringConfig(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	*/
	
	/*
	private EntityManager em;
	
	@Autowired
	public SpringConfig(EntityManager em) {
		this.em = em;
	}
	*/
	
	// 이거 쓰면 스프링데이터jpa가 알아서 등록해서 인젝션해줌. 
	private final MemberRepository memberRepository;
	
	@Autowired
	public SpringConfig(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	@Bean
	public MemberService memberServic() {
		return new MemberService(memberRepository); // MemberService 생성자는 MemberRepository 파라미터가 필요
	}

	/*
	@Bean
	public MemberRepository memberRepository() {
		// return new MemoryMemberRepository(); --> 설정만 바꿔서 구현 클래스 변경 가능 (객체 지향 다형성)
		// return new JdbcMemberRepository(dataSource);
		// return new JdbcTemplateMemberRepository(dataSource);
		return new JapMemberRepository(em);
	}
	*/
}
