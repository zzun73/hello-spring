package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller //컨트롤러 사용시 작성해야하는 어노테이션
public class HelloController {

    @GetMapping("hello") // 웹어플리케이션에서 /hello 입력시 어노테이션 아래에 있는 메서드 호출함 (index.html에서 href로 /hello 호출됨)
    public String hello(Model model) {
        model.addAttribute("data", "hello!!"); // 키: data  값: hello!!
        return "hello"; // 컨트롤러에서 리턴값으로 문자 반환시 viewResolver가 화면을 찾아 처리해줌 resources/templates/ 리턴값.html
    }

    // @RequestParam : 요청의 파라미터를 연결할 매개변수에 붙이는 어노테이션(매개변수 앞에 붙이면 required = true 로 처리)
    // required = false 일 경우 ?= 형태 입력 안하면 400에러가 아닌 null로 처리됨
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(value = "name") String name, Model model) { // http get 방식 ?name=spring! 형태
        model.addAttribute("name", name);  // 2번째 매개변수인 String 타입의 name이 사용자가 입력한 값
        return "hello-template";
    }

    // 문자 반환
    @GetMapping("hello-string")
    @ResponseBody // 응답 본문인 html body에 직접 데이터를 전달해 html tag 존재x (view 없이 문자데이터 그대로 요청한 클라이언트에 내려감)
    public String helloString(@RequestParam(value = "name") String name) {

        return "hello " + name; // name에 사용자의 입력값이 들어감
    }


    // 객체 반환 (hello-api?key=value시  json형태 {key:value} 반환)
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name); // key = name, value = 입력값
        return hello;  // 객체 반환 형태 default -> json
    }

    // getter and setter 생성 단축키 alt + insert
    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }
}
