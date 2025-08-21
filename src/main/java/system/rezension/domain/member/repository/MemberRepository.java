package system.rezension.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import system.rezension.domain.member.entity.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByUsername(String username);
}
