package by.home.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("user/{id}/edit", "/user/new", "user/{id}/edit?")
                .hasAuthority("ROLE_ADMIN")
                .and()
                .authorizeRequests()
                .antMatchers("/user", "/user/{id}", "/login")
                .hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                .antMatchers("/login", "/css/**", "/css/main.css", "/head/**", "/navBar/**", "/scripts/**", "/webjars/**", "/footer/**", "/webjars/**")
                .permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .defaultSuccessUrl("/user")
                .and()
                .logout()
                .permitAll();
    }

    //
    @SuppressWarnings("deprecation")
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
