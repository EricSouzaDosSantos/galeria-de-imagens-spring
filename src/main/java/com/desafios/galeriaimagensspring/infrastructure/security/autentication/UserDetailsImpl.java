package com.desafios.galeriaimagensspring.infrastructure.security.autentication;

import com.desafios.galeriaimagensspring.core.model.enums.UserRole;
import com.desafios.galeriaimagensspring.infrastructure.persistence.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@AllArgsConstructor
public class UserDetailsImpl implements UserDetails {

    private final UserEntity user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (user.getUserRole() == UserRole.ROLE_USER) return List.of(new SimpleGrantedAuthority("ROLE_USER"), new SimpleGrantedAuthority("ROLE_ViEWER"));
        else return List.of(new SimpleGrantedAuthority("ROLE_VIEWER"));
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return getUser().getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
