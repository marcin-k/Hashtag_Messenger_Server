package com.marcin.hashtagmessenger.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);

    //in memeory for testing puposes
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("user1").password("{noop}user1").roles("USER");
        logger.info("password for user user1 is user1");
    }

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
    }
}
