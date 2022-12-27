package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository {  /** 인터메이스 메서드 구현: Alt + Enter */

    private static Map<Long, Member> store = new HashMap<>(); // 실무에서는 동시성 문제 발생함
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); //Optional.ofNullable 사용시 null일 경우에도 클라이언트에서 처리 가능.
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name)) // member에 들어있는 name과 파라미터의 name 비교
                .findAny(); // 찾을 경우 바로 그 값 반환, 없을 경우 Optional에 null을 포함해 반환
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
