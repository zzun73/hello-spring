package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller //컨트롤러 사용시 작성해야하는 어노테이션
public class HelloController {

    @GetMapping("hello") // 웹어플리케이션에서 /hello 입력시 어노테이션 아래에 있는 메서드 호출함 (index.html에서 href로 /hello 호출됨)
    public String hello(Model model){
        model.addAttribute("data","hello!!"); // 키: data  값: hello!!
        return "hello"; // 컨트롤러에서 리턴값으로 문자 반환시 viewResolver가 화면을 찾아 처리해줌 resources/templates/ 리턴값.html
    }
}
