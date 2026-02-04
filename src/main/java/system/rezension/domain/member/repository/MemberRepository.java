package system.rezension.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import system.rezension.domain.member.entity.Member;
import system.rezension.domain.member.entity.Role;

import java.util.Optional;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
    
    List<Member> findByRole(Role role);
}