package org.anyframe.cloud.auth.user.interfaces.facade.dto;

public class IsRegistered {

	private boolean isRegistered;

	public IsRegistered() {
	}

	public IsRegistered(boolean isRegistered) {
		this.isRegistered = isRegistered;
	}

	public boolean isRegistered() {
		return isRegistered;
	}

	public void setRegistered(boolean isRegistered) {
		this.isRegistered = isRegistered;
	}
	
}
