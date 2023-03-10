package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

// 서비스 클래스의 메서드 네이밍은 비지니스에 의존적으로 설계
// @Service // 컴포넌트 스캔 방식 사용
@Transactional // jpa는 join할 때 모든 데이터 변경이 트랜잭션 안에서 수행되어야 함 (데이터를 저장하거나 변경할때 필요함)
public class MemberService {

    private final MemberRepository memberRepository;

    /** 생성자 생성: Alt + Insert */

    // 직접 new 연산자를 이용하여 생성하는 것이 아니라 외부에서 선언하고 이를 주입받아 사용하는 DI (Dependency Injection)
    // @Autowired // 컴포넌트 스캔 방식 사용
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // DI setter 주입 사용시
//    private MemberRepository memberRepository;
//
//    @Autowired
//    public void setMemberRepository(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }

    /**
     * 회원 가입
     */
    public Long join(Member member) {

        validateDuplicateMember(member); //중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) { // 중복 회원 검사
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    /**
     * 특정 회원 조회
     */
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }

}
