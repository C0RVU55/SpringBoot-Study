package hello.hellospring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;

@Service
public class MemberService {

	// 테스트 파일 생성 단축키 --> ctrl+N --> junit 검색 후 test case
	
	// private final MemberRepository memberRepository = new MemoryMemberRepository();
	private final MemberRepository memberRepository;
	
	// 테스트할 때 MemberRepository를 새로 선언하지 않고 같은 repository를 쓰기 위해 생성자 만듦
	// MemberService 입장에서 직접 new 하지 않고 외부에서 repository를 받음 --> 의존성 주입 (Dependency Injection, DI)
	// @Autowired --> @Bean 등록했으니까 뺌?
	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	// 회원가입
	public Long join(Member member) {
		
		// 이름 중복 불가
		//Optional<Member> result = memberRepository.findByName(member.getName());
		// result.orElseGet() --> 값이 있으면 꺼내기 이런 것도 있음
		// null이 아니면 아래와 같이 (optional을 썼기 때문에 if문 안 써도 됨)
		/*
		result.ifPresent(m -> {
			throw new IllegalStateException("이미 존재하는 회원입니다.");
		});
		*/
		
		validateDuplicateMember(member); // 중복 회원 검증
		memberRepository.save(member);
		return member.getId();
	}
	
	// 중복 회원 검증
	public void validateDuplicateMember(Member member) {
		// 또는 이미 optional로 result가 반환됐으니까 아래처럼 생략할 수도 있음 --> 로직이니까 메소드로 따로 빼기
		memberRepository.findByName(member.getName())
			.ifPresent(m -> {
				throw new IllegalStateException("이미 존재하는 회원입니다.");
			});
	}
	
	// 전체회원 조회
	public List<Member> findMembers(){
		return memberRepository.findAll();
	}
	
	// 아이디 찾기
	public Optional<Member> findOne(Long memberId){
		return memberRepository.findById(memberId);
	}

}
