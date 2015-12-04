package org.anyframe.cloud.auth.security.domain;

import org.anyframe.cloud.auth.repository.domain.UserResource;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 * Created by Hahn on 2015-12-04.
 */
public class AnyframeUserDetails implements UserDetails {

    public static final String SCOPE_READ = "read";

    public static final String SCOPE_WRITE = "write";

    public static final String ROLE_USER = "ROLE_USER";

    private Collection<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();

    private UserResource userResource;

    public AnyframeUserDetails(UserResource userResource) {
        Assert.notNull(userResource, "the provided userResource reference can't be null");
        this.userResource = userResource;
        for (String ga : Arrays.asList(ROLE_USER, SCOPE_READ, SCOPE_WRITE)) {
            this.grantedAuthorities.add(new SimpleGrantedAuthority(ga));
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return userResource.getPassword();
    }

    @Override
    public String getUsername() {
        return userResource.getUserId();
    }

    @Override
    public boolean isAccountNonExpired() {
        return isEnabled();
    }

    @Override
    public boolean isAccountNonLocked() {
        return isEnabled();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isEnabled();
    }

    @Override
    public boolean isEnabled() {
        return userResource.isEnabled();
    }
}
