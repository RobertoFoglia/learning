package com.xantrix.webapp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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

    /*
    // @@@ hard-coding users
    // it is replaced with CustomUserDetailService
    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        User.UserBuilder users = User.builder();
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();

        // @@@ 	User and roles creation
        manager.createUser(
                users
                        .userId("Nicola")
                        .password(new BCryptPasswordEncoder().encode("123Stella"))
                        .roles("USER")
                        .build());

        manager.createUser(
                users
                        .userId("Admin")
                        .password(new BCryptPasswordEncoder().encode("VerySecretPwd"))
                        .roles("USER", "ADMIN")
                        .build());

        return manager;
    }
*/
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

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder,
                                @Qualifier("customUserDetailsService") UserDetailsService userDetailsService) throws Exception {
        authenticationManagerBuilder.userDetailsService(userDetailsService)
                .passwordEncoder(new BCryptPasswordEncoder());
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
