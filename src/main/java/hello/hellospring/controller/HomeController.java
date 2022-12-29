package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/") // 스프링 컨테이너를 먼저 확인하기 때문에 맵핑 성공시 static에 있는 welcome page(index.html)은 무시됨
    public String home(){
        return "home";
    }
}
