package com.desafios.galeriaimagensspring.infrastructure.security.autentication;

import com.desafios.galeriaimagensspring.core.gateways.UserGateway;
import com.desafios.galeriaimagensspring.core.model.User;
import com.desafios.galeriaimagensspring.interfaces.mapper.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserGateway userGateway;
    private final UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userGateway.findByEmail(username).orElseThrow( () -> new UsernameNotFoundException("user not found with email: " + username));
        return new UserDetailsImpl(userMapper.toEntity(user));
    }
}
