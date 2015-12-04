package org.anyframe.cloud.auth.user.application;

import org.anyframe.cloud.auth.user.domain.RegisteredUser;

public interface ApplicationEvents {

	void userRegistered(RegisteredUser registeredUser);
}
