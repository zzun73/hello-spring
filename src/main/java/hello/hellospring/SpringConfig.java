package hello.hellospring;

import hello.hellospring.repository.*;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    // 스프링 데이터 JPA 사용 안 할 경우
//    private final DataSource dataSource;
//    private final EntityManager em;
//    @Autowired
//    public SpringConfig(DataSource dataSource, EntityManager em) {
//        this.dataSource = dataSource;
//        this.em = em;
//    }
//    @Bean
//    public MemberService memberService() {
//        return new MemberService(memberRepository()); /** 파라미터 정보 확인: Ctrl + p */
//    }
//
//    @Bean
//    public MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//        return new JdbcMemberRepository(dataSource); // Jdbc 사용시 (OCP)
//        return new JdbcTemplateMemberRepository(dataSource); // JdbcTemplate 사용시 (순수 Jdbc API 반복 코드를 대부분 제거, SQL는 직접 작성 필요)
//        return new JpaMemberRepository(em); // JPA 사용시
//    }

    // AOP를 직접 bean에 등록하는 방법
//    @Bean
//    public TimeTraceAOP timeTraceAOP() {
//        return new TimeTraceAOP();
//    }

    // 스프링 데이터 JPA 사용할 경우
    private final MemberRepository memberRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository); /** 파라미터 정보 확인: Ctrl + p */
    }


}
