package hello.hellospring.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;

class MemberServiceTest {

	// 인텔리제이는 메소드 선택 가능한데 이클립스는 안 돼서 내가 일일이 쳐야 되는듯.
	// 테스트할 때는 메소드명을 한글로 적어도 됨. 테스트 코드는 빌드에 포함되지 않음.
	// 테스트는 예외처리가 중요함.
	
	// MemberService memberService = new MemberService();
	MemberService memberService;
	MemoryMemberRepository memberRepository = new MemoryMemberRepository();
	// 서비스라서 저장하는 게 없으니까 Repository 불러옴. 
	// static이긴 하지만 한 번 더 인스턴스화했기 때문에 다른 Repository를 쓰고 있는 상황. 
	
	@BeforeEach
	public void beforeEach() {
		memberRepository = new MemoryMemberRepository();
		memberService = new MemberService(memberRepository);
	}
	// 테스트 실행하기 전에 repository 선언해서 memberService에 있는 생성자에 넣어서 씀 --> 같은 repository로만 쓰게 됨
	
	@AfterEach
	public void afterEach() {
		memberRepository.clearStore();
	}
	
	@Test
	void join() {
		// 테스트 코드 권장 구조
		// given
		Member member = new Member();
		member.setName("hello");
		
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
		
		/* try catch 써도 되지만 다른 문법도 있음 --> assertThrows
		try {
			memberService.join(member2);
			fail("예외가 발생해야 합니다.");
		} catch (IllegalStateException e) {
			Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.ㅇㄹ");
		}
		*/
	}
	
	@Test
	void findMembers() {
		
	}
	
	@Test
	void findOne() {
		
	}

}
