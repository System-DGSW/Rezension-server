package system.rezension.global.security;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import system.rezension.domain.member.entity.Member;

import java.util.Collection;

@Getter
public class CustomUserDetails implements UserDetails {

    private final Long id;
    private final String username;
    private final String password;
    private final Collection<? extends GrantedAuthority> authorities;
    private final boolean enabled;

    public CustomUserDetails(Member member, Collection<? extends GrantedAuthority> authorities) {
        this.id = member.getId();
        this.username = member.getUsername();
        this.password = member.getPassword();
        this.authorities = authorities;
        this.enabled = true; // 필요하면 Member 상태에 따라 바꿀 수 있음
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // 필요하면 Member 상태 체크
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // 필요하면 Member 상태 체크
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // 필요하면 Member 상태 체크
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
