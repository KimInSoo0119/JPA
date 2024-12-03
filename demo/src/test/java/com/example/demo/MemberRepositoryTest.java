package com.example.demo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
class MemberRepositoryTest {

	@Autowired MemberRepository memberRepository;
	
	@Test
	@Transactional
	@Rollback(false)
	/*EntityManager를 통한 모든 데이터변경은 transaction 안에서 이루어져야 한다. 여기 말고 MemberRepository 선언해도됨*/
	/*Transactional이 테스트케이스에 있을경우 트랜잭션이후, 롤백*/
	public void testMember() throws Exception {
		//given
		Member member = new Member();
		member.setUsername("memberA");
		
		//when
		Long saveId = memberRepository.save(member);
		Member findMember = memberRepository.find(saveId);
		
		//then
		Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
		Assertions.assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
		/*같은 트랜잭션 즉, 영속성 컨텍스트에 저장되어있음, 같은 엔티티*/
		Assertions.assertThat(findMember).isEqualTo(member); 
	}
}
