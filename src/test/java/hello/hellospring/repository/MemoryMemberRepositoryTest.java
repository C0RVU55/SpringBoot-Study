package hello.hellospring.repository;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import hello.hellospring.domain.Member;

public class MemoryMemberRepositoryTest {
	
	// 테스트 폴더에 같은 이름의 패키지 생성하고 같은 이름+test 클래스 파일 생성함
	// public 아니어도 됨. junit으로 테스트.
	// 패키지 우클릭해서 junit으로 돌리면 전체 클래스를 한번에 테스트 가능.
	
	MemoryMemberRepository repository = new MemoryMemberRepository();
	
	// 메소드 끝날 때마다 동작하는 콜백 메소드
	@AfterEach
	public void afterEach() {
		repository.clearStore();
	}
	
	@Test
	public void save() {
		Member member = new Member();
		member.setName("spring"); // shift+enter 바로 내려옴
		
		repository.save(member);
		Member result = repository.findById(member.getId()).get(); // id 저장 후 값 꺼내옴
		
		//System.out.println("result = "+ (result == member)); 이렇게 해도 되지만 일일이 하기 힘드니까 아래처럼 주피터 어썰트
		//Assertions.assertEquals(member, result); / (기대하는 값, 나오는 값) --> 참이면 초록색 거짓이면 오류남
		
		//또는 org.assertj.core.api도 있는데 더 편하게 쓸 수 있는 거
		Assertions.assertThat(member).isEqualTo(result); // Assertions를 static으로 선언해서 assertThat만 바로 나오게 할 수 있음(단축키 찾기)
		
	}
	
	@Test
	public void findByName() {
		Member member1 = new Member();
		member1.setName("spring1");
		repository.save(member1);
		
		Member member2 = new Member(); // 같은 단어 찾아서 바꾸기 alt+shift+R
		member2.setName("spring2");
		repository.save(member2);
		
		Member result = repository.findByName("spring1").get();
		Assertions.assertThat(result).isEqualTo(member1);
	}
	
	// 다시 실행하면 findByName이 오류나는데 테스트 순서는 각 메소드가 각자 실행되고 여기서는 findAll이 먼저 실행돼서 
	// member1에 다른 주소값이 배정된 상태라 오류남. 그래서 테스트 설계는 순서에 의존적이면 안 됨.
	// 또 MemoryMemberRepository와 여기에 테스트 끝날 때마다 repository 데이터를 지우는 코드를 추가해야 됨.
	
	@Test
	public void findAll() {
		Member member1 = new Member();
		member1.setName("spring1");
		repository.save(member1);
		
		Member member2 = new Member();
		member2.setName("spring2");
		repository.save(member2);
		
		List<Member> result = repository.findAll();
		
		Assertions.assertThat(result.size()).isEqualTo(2);
	}
}
