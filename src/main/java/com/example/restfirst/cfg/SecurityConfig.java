package com.example.restfirst.cfg;

import com.example.restfirst.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private final PasswordEncoder passwordEncoder;
  @Autowired
  public SecurityConfig( PasswordEncoder passwordEncoder){
    this.passwordEncoder = passwordEncoder;
  }


  @Override
  public void configure(HttpSecurity httpSecurity) throws Exception {
    httpSecurity
            .csrf().disable()
            .authorizeRequests()
            .antMatchers(HttpMethod.POST,"/api/v1/**")
            .hasRole(Role.ADMIN.name()).anyRequest().authenticated()
            .and()
            .httpBasic()
            .and().cors().and();
  }

  @Bean
  @Override
  protected UserDetailsService userDetailsService() {

    return new InMemoryUserDetailsManager(
            User.builder()
                    .username("user")
                    .password(passwordEncoder.encode("admin"))
                    .roles("ADMIN")
                    .build()
    );
  }

}