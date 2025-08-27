package system.rezension.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import system.rezension.domain.member.entity.Member;
import system.rezension.domain.question.entity.Subscription;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    List<String> findEmailsBySubscription(Subscription subscription);
}
