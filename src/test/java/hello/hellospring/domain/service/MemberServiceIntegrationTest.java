package hello.hellospring.domain.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.service.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {

    @Autowired  MemberService memberService;
    @Autowired  MemberRepository memberRepository;



    @Test
    void 회원가입() {
        //given : 뭔가가 주어졌을 때
        Member member = new Member();
        member.setName("spring");

        // when : 이걸 실행했을 때

        Long saveId = memberService.join(member);

        // then : 결과가 이게 나와야 해
        Member findMember = memberService.findOne(saveId).get();
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
    }


    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
       org.junit.jupiter.api.Assertions.assertThrows(IllegalStateException.class, () -> memberService.join(member2));
//        try{
//            memberService.join(member2); // 예외가 발생해야 한다.
//            Assert.fail();
//        } catch (IllegalStateException e){
//            Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.123123123");
//        }

            //then : 결과가 이게 나와야 해
    }


    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}