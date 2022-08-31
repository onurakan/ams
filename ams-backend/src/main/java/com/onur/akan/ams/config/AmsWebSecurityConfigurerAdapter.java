package com.onur.akan.ams.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class AmsWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

    private String API_URL_PREFIX="/api/v1";
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user").password("{noop}password").roles("USER")
                .and()
                .withUser("admin").password("{noop}password").roles("USER", "ADMIN");
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                //HTTP Basic authentication
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, API_URL_PREFIX+"/assets/**").hasRole("USER")
                .antMatchers(HttpMethod.POST, API_URL_PREFIX+"/assets").hasRole("USER")
                .antMatchers(HttpMethod.PUT, API_URL_PREFIX+"/assets/**").hasRole("USER")
                .antMatchers(HttpMethod.DELETE, API_URL_PREFIX+"/assets/**").hasRole("ADMIN")
                .and()
                .csrf().disable()
                .formLogin().disable();
    }
}
