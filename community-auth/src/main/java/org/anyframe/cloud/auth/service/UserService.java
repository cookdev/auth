package org.anyframe.cloud.auth.service;

import org.anyframe.cloud.auth.repository.domain.UserResource;

import java.util.Collection;

/**
 * Created by Hahn on 2015-12-04.
 */
public interface UserService {

    UserResource getUserById(String id);

    Collection<UserResource> getAllUsers();

    UserResource create(UserResource userResource);
}
