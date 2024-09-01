package com.stuntingdetection.stuntingdetection.Security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    public final UserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/error", "/swagger-ui.html/**", "/v2/api-docs", "/swagger-resources/**", "/swagger-ui/**", "/webjars/**").permitAll()
                .antMatchers("/registration", "/refresh-token", "/registration-select/{roleId}", "/registration-default").permitAll()
                .antMatchers("/login/**").permitAll()
                .antMatchers("/api/deteksi-stunting/deteksi").hasAnyAuthority("ADMIN")
                .antMatchers("/api/deteksi-stunting/deteksi").hasAnyAuthority("USER")
                .antMatchers("add-rule").hasAnyAuthority("ADMIN")
                .antMatchers("/get-all-rule").hasAnyAuthority("ADMIN")
                .antMatchers("update-rule/{id}").hasAnyAuthority("ADMIN")
                .antMatchers("delete-rule/{id}").hasAnyAuthority("ADMIN")
                .antMatchers("/user/register").permitAll()
                .antMatchers("/user/submit").hasAnyAuthority("ADMIN")
                .antMatchers("/user/submit", "/user/select-all", "/user/arrays").hasAnyAuthority("USER")
                .anyRequest().authenticated();

        http.addFilterBefore(new CustomizeAuthorFilterConfig(), UsernamePasswordAuthenticationFilter.class);
        http.addFilter(new CustomizeFilterConfig(authenticationManagerBean()));
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/v2/api-docs/**",
                "/swagger.json",
                "/swagger-ui.html",
                "/swagger-resources/**",
                "/webjars/**",
                "/swagger-ui/**");
    }

}

