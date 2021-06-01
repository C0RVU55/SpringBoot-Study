package hello.hellospring.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import hello.hellospring.domain.Member;

public class JapMemberRepository implements MemberRepository{

	// jpa는 EntityManager가 다 관리함
	private final EntityManager em;
	
	// 만든 엔티티매니저를 인젝션 받으면 됨.
	public JapMemberRepository(EntityManager em) {
		this.em = em;
	}

	@Override
	public Member save(Member member) {
		// 이걸로 끝임. 알아서 다 set해 줌.
		em.persist(member);
		return member;
	}

	@Override
	public Optional<Member> findById(Long id) {
		// TODO Auto-generated method stub
		Member member = em.find(Member.class, id);
		return Optional.ofNullable(member);
	}

	@Override
	public Optional<Member> findByName(String name) {
		List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
				.setParameter("name", name)
				.getResultList();
				
		return result.stream().findAny(); // 조건 이름으로 아이디 하나만 찾으니까 추가?
	}

	@Override
	public List<Member> findAll() {
		// 여기서는 쿼리문을 써야 되긴 한데 PK기반으로 찾는 게 아니면 jpq라는 쿼리문을 써야 함 
		// --> 객체를 대상으로 쿼리문을 날리면 sql로 번역됨. m은 객체 자체를 select한다는 거.
		List<Member> result = em.createQuery("select m from Member m", Member.class)
				.getResultList();
		return result;
	}

}
