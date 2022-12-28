package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

//기본적으로 컴포넌트 스캔은 public static void main 메서드가 포함 되어 있는 패키지와 그 하위 패키지만 자동 스캔의 대상이 됨
@Controller  // @Component 애노테이션이 들어 있어 스프링 빈으로 자동 등록됨 (컴포넌트 스캔 방식) -@Service, @Repository 도 마찬가지
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
