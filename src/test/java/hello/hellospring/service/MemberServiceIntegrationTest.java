package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/** 특정 클래스 테스트 코드 파일 생성: Ctrl + Shift + t */
@SpringBootTest // 스프링 컨테이너와 테스트를 함께 실행
@Transactional // 테스트 케이스에  이 애노테이션이 있을 경우 commit을 하는게 아니라 rollback 시킴
              // (테스트 시작 전 트랙잭션 시작 테스트 완료 후 롤백 -> DB에 데이터가 남지 않아 다음 테스트에 영향 X)
class MemberServiceIntegrationTest {

    // 테스트할때는 가장 편한 필드 주입 사용
    @Autowired  MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
//    @Commit // 이 애노테이션 사용시 트랜잭션이 있어도 커밋 수행함
    void 회원가입() {
        //Given
        Member member = new Member();
        member.setName("spring");

        //When
        Long saveId = memberService.join(member);

        //Then
        Member findMember = memberRepository.findById(saveId).get();
        assertEquals(member.getName(), findMember.getName());
    }

    @Test
    public void 중복_회원_예외() {
        //Given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //When
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class,
                () -> memberService.join(member2));//예외가 발생해야 한다.
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }

}