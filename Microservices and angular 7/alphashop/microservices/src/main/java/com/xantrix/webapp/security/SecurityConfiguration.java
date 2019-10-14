package com.xantrix.webapp.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    /* categorizes the error they can occur */
    // only login and password are wrong
    public final static String REALM = "REAME";

    private static final String[] USER_MATCHER = {"/api/articoli/cerca/**"};
    private static final String[] ADMIN_MATCHER = {"/api/articoli/inserisci/**",
            "/api/articoli/modifica/**", "/api/articoli/elimina/**"};

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        User.UserBuilder users = User.builder();
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();

        // @@@ 	User and roles creation
        manager.createUser(
                users
                        .username("Nicola")
                        .password(new BCryptPasswordEncoder().encode("123Stella"))
                        .roles("USER")
                        .build());

        manager.createUser(
                users
                        .username("Admin")
                        .password(new BCryptPasswordEncoder().encode("VerySecretPwd"))
                        .roles("USER", "ADMIN")
                        .build());

        return manager;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // csrf configure the protection from a hacker attach, but in the service is not necessary
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers(USER_MATCHER).hasAnyRole("USER")
                .antMatchers(ADMIN_MATCHER).hasAnyRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                // setting of page error fot the login error
                .httpBasic().realmName(REALM).authenticationEntryPoint(getBasicAuthEntryPoint())
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Bean
    public AuthEntryPoint getBasicAuthEntryPoint() {
        return new AuthEntryPoint();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // useful for the frontend
        web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
    }
}
