package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    // test가 끝날때마다 깔끔하게 지워주기
    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("김보연");

        repository.save(member);
        Member result = repository.findById(member.getId()).get();
        assertThat(member).isEqualTo(result);

//        System.out.println("result = " + (result == member));
//        Assertions.assertEquals(member, result);
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("김보연");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("박재민");
        repository.save(member2);

        Member result = repository.findByName("김보연").get();

        assertThat(result).isEqualTo(member1); // true
//        assertThat(result).isEqualTo(member2); // false
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("김보연");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("박재민");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }
}
