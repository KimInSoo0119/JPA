package com.example.demo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
class MemberRepositoryTest {

	@Autowired MemberRepository memberRepository;
	
	@Test
	@Transactional 
	/*EntityManager를 통한 모든 데이터변경은 transaction 안에서 이루어져야 한다. 여기 말고 MemberRepository 선언해도됨*/
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
	}
}
