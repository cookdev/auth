package org.anyframe.cloud.auth.repository;

import org.anyframe.cloud.auth.repository.domain.UserResource;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the UserResource entity.
 */
public interface UserRepository extends JpaRepository<UserResource, String> {
}
