/**
 * 
 */
package com.mycloud.enums;

/**
 * @author Shawn
 *
 */
public enum State {

	ACTIVE("Active"), INACTIVE("Inactive"), DELETED("Deleted"), LOCKED("Locked");

	private String value;

	private State(final String state) {
		this.value = state;
	}

	public String getState() {
		return this.value;
	}

	public static State getScope(String value) {
		for (State scope : State.values()) {
			if (scope.value.equalsIgnoreCase(value)) {
				return scope;
			}
		}
		throw new IllegalArgumentException("Illegal State value: " + value);
	}

	@Override
	public String toString() {
		return this.value;
	}


}
