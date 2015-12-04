package org.anyframe.cloud.auth.repository.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Hahn on 2015-12-04.
 */
@Entity
public class UserResource {

    @Id
    @Column(name="user_id")
    private String userId;

    private String password;

    private boolean enabled;

    public String getUserId() { return userId; }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
