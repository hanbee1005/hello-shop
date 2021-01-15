package sample.jpa.helloshop.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import sample.jpa.helloshop.Repository.MemberRepository;
import sample.jpa.helloshop.domain.Member;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    @Commit
    void 회원가입() throws Exception {
        // Given
        Member member = new Member();
        member.setName("son");

        // When
        Long saveId = memberService.join(member);

        // Then
        assertEquals(member, memberRepository.findOne(saveId));
    }

    @Test
    void 중복_회원_예외() {
        // Given
        Member member1 = new Member();
        member1.setName("kim");

        Member member2 = new Member();
        member2.setName("kim");

        // When
        try {
            memberService.join(member1);
            memberService.join(member2);  // 예외 발생
        } catch (IllegalStateException e) {
            // Then
            assertEquals("이미 같은 이름의 회원이 존재합니다.", e.getMessage());
        }
    }

    @Test
    void 전체_회원_조회() {
        // Given
        Member member1 = new Member();
        member1.setName("kim");

        Member member2 = new Member();
        member2.setName("park");

        memberService.join(member1);
        memberService.join(member2);

        // when
        List<Member> findMembers = memberService.findMembers();

        // Then
        assertEquals(2, findMembers.size());
    }

    @Test
    void findOne() {
        // Given
        Member member = new Member();
        member.setName("kim");

        Long saveId = memberService.join(member);

        // When
        Member findMember = memberService.findOne(saveId);

        // Then
        assertEquals(saveId, findMember.getId());
    }
}