package hello.hellospring.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import hello.hellospring.domain.Member;

public interface SpringDataJapMemberRepository extends JpaRepository<Member, Long>, MemberRepository{

	// 인터페이스끼리는 extends로 상속하고 다중 상속 가능
	// 스프링jpa가 자동으로 bean 만들어줌
	
	// 메소드명 쓰고 ctrl+space하면 자동으로 아래처럼 메소드 생김
	/*
	@Override
	default Optional<Member> findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}
	*/
	
	// 근데 그냥 이렇게 쓰면 끝남.
	// 근데 비즈니스 로직은 다 다른데 어떻게? --> findByName처럼 형식이 정해져 있어서 갖다 쓰면 됨.
	@Override
	Optional<Member> findByName(String name);
	// JSQL select m from member m where m.name = ?
	
	// 이거 테스트할 때 Bean 하나 있는데 중복된다는 오류 떴는데 MemoryMemberRepository에 붙은 @Repository 떼면됨.
	// 이미 자동으로 스프링데이터jpa는 이미 자동으로 memberRepository를 주입하고 있는 상태이기 때문.
	// 또 처음에 memberServic라고 오타낸 거 있는데 이거 수정하면 오류남.
}
