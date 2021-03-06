package com.berachad.patientsorm.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    // Application.prop...
    @Autowired
    private DataSource dataSource;

    @Override

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder passwordEncoder = passwordEncoder();
        //  First Methode Basic Authentification
        /*
            String encodedPWD = passwordEncoder.encode("1234");
            System.out.println(encodedPWD);
            auth.inMemoryAuthentication().withUser("user1").password(encodedPWD).roles("USER");
            auth.inMemoryAuthentication().withUser("user2").password(passwordEncoder.encode("1234")).roles("USER");
            auth.inMemoryAuthentication().withUser("admin").password(passwordEncoder.encode("1234")).roles("USER", "ADMIN");
        */
        // Secend Methode JDBC Authentification

            auth.jdbcAuthentication()
           .dataSource(dataSource)
           .usersByUsernameQuery("select username as principal, password as credentials, active from users where username=?")
           .authoritiesByUsernameQuery("select username as principal , role as role from users_roles where username =?")
           .rolePrefix("ROLE_")
           .passwordEncoder(passwordEncoder);


        //  third Methode Authentification with details Service

   /*     auth.userDetailsService(new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return null;
            }
        });*/
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {


        // All of this urls should have admin role
        http.authorizeRequests().antMatchers("/").permitAll();
        http.authorizeHttpRequests().antMatchers("/admin/**").hasRole("ADMIN");
        http.authorizeHttpRequests().antMatchers("/user/**").hasRole("USER");
        // http.authorizeHttpRequests().anyRequest().authenticated();
        http.exceptionHandling().accessDeniedPage("/403");
        http.formLogin();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
