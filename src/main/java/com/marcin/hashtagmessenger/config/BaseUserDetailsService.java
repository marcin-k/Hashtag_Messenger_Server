package com.marcin.hashtagmessenger.config;

import com.marcin.hashtagmessenger.core.BaseUser;
import com.marcin.hashtagmessenger.core.BaseUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class BaseUserDetailsService implements UserDetailsService {

    @Autowired
    private BaseUserRepository baseUserRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        BaseUser userAccount = baseUserRepository.findByUsername(s);
//        System.out.println(userAccount.getPassword());
        if (userAccount != null) {
            User.UserBuilder builder = User.withUsername(s);
            builder.password(userAccount.getPassword());
            builder.roles("USER");
            return builder.build();
        } else {
            throw new UsernameNotFoundException("UserAccount not found.");
        }
    }
}
