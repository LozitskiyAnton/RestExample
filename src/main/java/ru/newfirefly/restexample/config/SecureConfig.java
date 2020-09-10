package ru.newfirefly.restexample.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import ru.newfirefly.restexample.model.Role;

@Configuration
@EnableWebSecurity
public class SecureConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {

       http.authorizeRequests().antMatchers("/json").permitAll()
       .antMatchers(HttpMethod.GET, "/api/**").hasAnyRole(Role.ADMIN.name(),Role.USER.name())
       .antMatchers(HttpMethod.POST, "/api/**").hasRole(Role.ADMIN.name())
       .antMatchers(HttpMethod.DELETE, "/api/**").hasRole(Role.ADMIN.name())
       .anyRequest().authenticated().and().httpBasic()
       ;
       http.csrf().disable();
    }
    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        return new InMemoryUserDetailsManager(User
                .builder()
                .password(passwordEncoder().encode("admin"))
                .username("admin")
                .roles(Role.ADMIN.name())
                .build(), User.builder()
                .password(passwordEncoder().encode("user"))
                .username("user")
                .roles(Role.USER.name()).build()
        );
    }

    @Bean
    protected PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(12);
    }
}
