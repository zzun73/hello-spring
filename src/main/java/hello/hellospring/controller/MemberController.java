package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

//기본적으로 컴포넌트 스캔은 public static void main 메서드가 포함 되어 있는 패키지와 그 하위 패키지만 자동 스캔의 대상이 됨
@Controller  // @Component 애노테이션이 들어 있어 스프링 빈으로 자동 등록됨 (컴포넌트 스캔 방식) -@Service, @Repository 도 마찬가지
public class MemberController {

// DI 생성자 주입 -> 좋은 방법
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
//       memberService.setMemberRepository(); // DI setter 주입 사용시 누구나 호출 가능해짐
    }

    @GetMapping(value = "/members/new") // 데이터 조회시 Get
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping(value = "/members/new") // 데이터 등록시 Post
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());
        memberService.join(member);
        return "redirect:/"; // 홈화면으로
    }

    @GetMapping(value = "/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }

    // DI 필드 주입 -> 안좋음 ( 중간에 수정 할 수 없음)
//    @Autowired private  MemberService memberService;


    // DI setter 주입 -> 누군가 멤버 컨트롤러를 호출했을때 setter의 접근제한이 public 이어야 함  (한 번 set하면 바꿀 일이 없는데 public으로 노출 되어있어 위험함)
//    private  MemberService memberService;
//
//    @Autowired
//    public void setMemberService(MemberService memberService) {
//        this.memberService = memberService;
//    }
}
