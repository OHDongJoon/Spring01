package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


public interface MemberRepository {
    /**
     * method         : save
     * author         : 오동준
     * date           : 2023/05/18
     * description    : 회원 저장
     */
    Member save(Member member);

    /**
     * method         : findById
     * author         : 오동준
     * date           : 2023/05/18
     * description    : 회원 아이디로 조회
     */
    Optional<Member> findById(Long id);

    /**
     * method         : findByName
     * author         : 오동준
     * date           : 2023/05/18
     * description    : 회원 이름으로 조회
     */
    Optional<Member> findByName(String name);
    List<Member> findAll();
}
