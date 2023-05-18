package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional // jpa를 사용하려면 항상 필요함
public class MemberService {
    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    /**
     * method         : join
     * author         : 오동준
     * date           : 2023/05/18
     * description    : 회원 가입
     */
    public Long join (Member member) {
        // 같은 이름이 있는 중복  회원 x

    validateDuplicateMember(member); // 중복 회원 검증
    memberRepository.save(member);
    return member.getId();
}





    /**
     * method         : validateDuplicateMember
     * author         : 오동준
     * date           : 2023/05/18
     * description    : 중복 회원 검증
     */
    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                        .ifPresent(m ->  { // ifPresent : 값이 있으면
                            throw new IllegalStateException("이미 존재하는 회원입니다.");
                        });
    }

    /**
     * method         : findMembers
     * author         : 오동준
     * date           : 2023/05/18
     * description    : 전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    /**
     * method         : findOne
     * author         : 오동준
     * date           : 2023/05/18
     * description    : 회원 조회
     */
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
