package org.anyframe.cloud.auth.security.service;

import org.anyframe.cloud.auth.repository.domain.UserResource;
import org.anyframe.cloud.auth.security.domain.AnyframeUserDetails;
import org.anyframe.cloud.auth.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Authenticate a user from the database.
 */
@Component("userDetailsService")
public class AnyframeUserDetailsService implements UserDetailsService {

    private final Logger log = LoggerFactory.getLogger(AnyframeUserDetailsService.class);

    @Autowired
    private UserService userService;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String login) {
    	
        log.debug("Authenticating {}", login);
        String lowercaseLogin = login.toLowerCase();
        UserResource userResource = userService.getUserById(lowercaseLogin);

        return new AnyframeUserDetails(userResource);
    }
}
