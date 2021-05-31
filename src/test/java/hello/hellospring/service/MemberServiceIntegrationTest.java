package hello.hellospring.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;

@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {

	// 이전까지는 자바 코드만 갖고 테스트한 거고 이번에는 DB연결해서 테스트 --> 위 @추가
	// Transactional : 테스트 시작 전 이거 하고 테스트 후 DB를 롤백해줌 
	// --> DB에 실제 데이터 반영 안 되니까 다음 테스트 바로 가능
	
	// 단위로 나눠서 컨테이너 없이 테스트할 수 있는 게 좋음
	
	// 기존 생성자로 받아도 되지만 테스트는 필드 갖고 해도 됨 --> BeforeEach, AfterEach 지움
	@Autowired MemberService memberService;
	@Autowired MemberRepository memberRepository;

	
	@Test
	void 회원가입() {
		// 테스트 코드 권장 구조
		// given
		Member member = new Member();
		member.setName("spring");
		
		// when
		Long saveId = memberService.join(member);
		
		// then
		Member findMember = memberService.findOne(saveId).get();
		assertThat(member.getName()).isEqualTo(findMember.getName());
	}
	
	@Test
	public void 중복_회원_예외() {
		//given
		Member member1 = new Member();
		member1.setName("spring");
		
		Member member2 = new Member();
		member2.setName("spring");
		
		// when
		memberService.join(member1);
		//memberService.join(member2);
		
		// assertThrows의 Assertions는 jupiter api / 오른쪽 로직을 실행하면 왼쪽 예외가 터져야 됨 / assertThrows 값 반환해줌
		IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2)); 
		
		// 메세지 테스트
		assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

	}


}
