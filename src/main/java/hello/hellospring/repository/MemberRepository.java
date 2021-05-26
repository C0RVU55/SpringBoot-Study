package hello.hellospring.repository;

import java.util.List;
import java.util.Optional;

import hello.hellospring.domain.Member;


public interface MemberRepository {
	
	// repository에 4가지 기능 생성
	Member save(Member member);
	Optional<Member> findById(Long id); //id가 null일 경우 Optional로 감싸서 반환
	Optional<Member> findByName(String name);
	List<Member> findAll();
}
