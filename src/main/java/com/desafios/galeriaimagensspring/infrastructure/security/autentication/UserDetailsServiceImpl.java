package com.desafios.galeriaimagensspring.infrastructure.security.autentication;

import com.desafios.galeriaimagensspring.infrastructure.persistence.entity.UserEntity;
import com.desafios.galeriaimagensspring.infrastructure.persistence.repository.user.JpaUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final JpaUserRepository jpaUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = jpaUserRepository.findByEmail(username).orElseThrow( () -> new UsernameNotFoundException("user not found with email: " + username));
        return new UserDetailsImpl(user);
    }
}
