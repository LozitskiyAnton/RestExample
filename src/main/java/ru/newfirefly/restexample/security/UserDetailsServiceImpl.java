package ru.newfirefly.restexample.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.newfirefly.restexample.model.User;
import ru.newfirefly.restexample.repository.UserRepositiory;

@Service("userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepositiory userRepositiory;

    @Autowired
    public UserDetailsServiceImpl(UserRepositiory userRepositiory) {
        this.userRepositiory = userRepositiory;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepositiory.findByEmail(email).orElseThrow(() ->
                new UsernameNotFoundException("User doesn't exist"));
        return SecurityUser.fromUser(user);
    }
}
