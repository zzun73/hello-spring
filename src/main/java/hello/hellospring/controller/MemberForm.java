package hello.hellospring.controller;

public class MemberForm {
    private String name; // createMemberForm.html의 input 태크에 name속성 값 "name"과 매칭

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
