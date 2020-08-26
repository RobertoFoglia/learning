package com.xantrix.webapp.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.net.URI;
import java.net.URISyntaxException;

@Service("customUserDetailsService")
public class CustomUserDetailService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(CustomUserDetailService.class);

    @Autowired
    private UserConfig userConfig;

    private static final RestTemplate restTemplate = new RestTemplate();

    @PostConstruct
    public void init() {
        restTemplate.getInterceptors().add(new BasicAuthenticationInterceptor(userConfig.getUserId(), userConfig.getPassword()));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Utenti utente = this.getHTTPValue(username);

        if (utente != null) {
            String[] roles = utente.getRuoli().parallelStream().map(role -> "ROLE_" + role).toArray(String[]::new);

            User.UserBuilder userBuilder =
                    User
                            .withUsername(utente.getUserId())
                            .disabled(utente.getAttivo().equals("Si") ? Boolean.FALSE : Boolean.TRUE)
                            .password(utente.getPassword())
                            .authorities(roles);
            return userBuilder.build();
        }

        return null;
    }

    private Utenti getHTTPValue(String username) {
        URI url = null;

        try {
            String srvUrl = userConfig.getSrvUrl();
            url = new URI(srvUrl + "/" + username);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        Utenti utente = null;

        try {
            utente = restTemplate.getForObject(url, Utenti.class);
        } catch (Exception e) {
            e.printStackTrace();
            logger.warn("Connessione al servizio di autenticazione non riuscita!!");
        }

        return utente;
    }
}
