package com.marcin.hashtagmessenger.config;
/************************************************************
 * Class used by Security config for login purposes gathers
 * username and password attribute to validate the login process
 *
 * author: Marcin Krzeminski
 *         x17158851
 * **************************************************************/
import com.marcin.hashtagmessenger.baseUser.BaseUser;
import com.marcin.hashtagmessenger.baseUser.BaseUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
