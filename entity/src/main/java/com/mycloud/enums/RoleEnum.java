/**
 * 
 */
package com.mycloud.enums;

/**
 * @author Shawn
 *
 */
public enum RoleEnum {
	
	ROLE_USER("ROLE_USER"),
	ROLE_ADMIN("ROLE_ADMIN"),
	
	;
	
	private String value;

    private RoleEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static RoleEnum getScope(String value) {
        for (RoleEnum scope : RoleEnum.values()) {
            if (scope.value.equalsIgnoreCase(value)) {
                return scope;
            }
        }
        throw new IllegalArgumentException("Illegal RoleEnum value: " + value);
    }

}
