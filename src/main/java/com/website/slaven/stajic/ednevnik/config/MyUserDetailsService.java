package com.website.slaven.stajic.ednevnik.config;

import com.website.slaven.stajic.ednevnik.model.MyUserDetails;
import com.website.slaven.stajic.ednevnik.model.User;
import com.website.slaven.stajic.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    UserRepository userRepository;

    @Autowired
    public void MyUserDetailsService(UserRepository tempUserRepository){
        this.userRepository = tempUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> user = userRepository.findByUserName(username);
        user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + username ) );
        return user.map(MyUserDetails::new).get();
    }
}
