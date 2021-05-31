package hello.hellospring;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import hello.hellospring.repository.JdbcMemberRepository;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.service.MemberService;

@Configuration
public class SpringConfig {

	private DataSource dataSource;

	@Autowired
	public SpringConfig(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Bean
	public MemberService memberServic() {
		return new MemberService(memberRepository()); // MemberService 생성자는 MemberRepository 파라미터가 필요
	}

	@Bean
	public MemberRepository memberRepository() {
		// return new MemoryMemberRepository(); --> 설정만 바꿔서 구현 클래스 변경 가능 (객체 지향 다형성)
		return new JdbcMemberRepository(dataSource);
	}
}
