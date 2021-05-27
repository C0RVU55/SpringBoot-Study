package hello.hellospring.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import hello.hellospring.domain.Member;

@Repository
public class MemoryMemberRepository implements MemberRepository{
	
	// MemoryMemberRepository가 구현체
	// 처음에 implements 하면 오류 나는데 Add method unimplements... 누르면 아래처럼 자동으로 오버라이드 됨
	// 아래 코드를 검증하기 위해 테스트 케이스를 작성해야 됨 --> junit이라는 프레임워크로 테스트함 --> test 폴더로
	
	private static Map<Long, Member> store = new HashMap<>();
	private static long sequence = 0L; // 실무에서는 동시성 문제 때문에 어텀롱 이러지만 여기선 간단하게 감

	@Override
	public hello.hellospring.domain.Member save(hello.hellospring.domain.Member member) {
		
		// id 시퀀스 1 올리고 id값 가져와서 store에 넣음.
		member.setId(++sequence);
		store.put(member.getId(), member);
		return member;
	}

	@Override
	public Optional<hello.hellospring.domain.Member> findById(Long id) {
		// null이 반환될 경우를 위해 optional 씀.
		return Optional.ofNullable(store.get(id));
	}

	@Override
	public Optional<hello.hellospring.domain.Member> findByName(String name) {
		// 아래는 람다식. 루프 돌다가 member값이 name과 같으면(조건 맞으면) 찾아서 반환.
		// 끝까지 돌렸는데 없으면 optional에 null값이 포함돼서 반환됨.
		return store.values().stream()
			.filter(member -> member.getName().equals(name))
			.findAny();
	}

	@Override
	public List<hello.hellospring.domain.Member> findAll() {
		// store는 map이지만 List로 바꿔서 반환.
		return new ArrayList<>(store.values());
	}
	
	// 저장소 지우는 메소드
	public void clearStore() {
		store.clear();
	}

}
