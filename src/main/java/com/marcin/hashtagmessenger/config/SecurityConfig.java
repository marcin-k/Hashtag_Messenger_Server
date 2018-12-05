package com.marcin.hashtagmessenger.config;
/************************************************************
 * Class used to configure the security and list of
 * included/excluded urls
 *
 * author: Marcin Krzeminski
 *         x17158851
 * **************************************************************/
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//    private static final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);

    @Autowired
    BaseUserDetailsService baseUserDetailsService;

    //in memory for testing purposes
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().withUser("user1").password("{noop}user1").roles("USER");
//        logger.info("password for user user1 is user1");
        auth.userDetailsService(baseUserDetailsService).passwordEncoder(passwordEncoder());

    }

    //used to store the password in the plan text format (for debugging purposes)
    @Bean
    public static NoOpPasswordEncoder passwordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }

    //security config with all urls added
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/**").access("hasRole('USER')")
                .and().formLogin()
                .and().httpBasic()
                .and().logout()
                .and().csrf().disable();
    }

    //excluded urls from security
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/api/parent/new");
        web.ignoring().antMatchers("/api/contacts/pw");
        web.ignoring().antMatchers("/api/contacts/login");
        web.ignoring().antMatchers("/api/contacts/checkLogin");
        web.ignoring().antMatchers("/api/contacts/parentOrChild");
        web.ignoring().antMatchers("/api/contacts/cache");
    }
}
