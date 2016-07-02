/**
 * 
 */
package com.mycloud.constant;

/**
 * @author Shawn
 *
 */
public enum LogisticsCompanyType {
	
	NORMAL("NORMAL"), 
	
	SPECIAL("SPECIAL"), 
	
	;

    private final String type;

    private LogisticsCompanyType(String type) {
        this.type = type;
    }

    public String type() {
        return type;
    }

    public static LogisticsCompanyType getStatus(String type) {
        for (LogisticsCompanyType stype : LogisticsCompanyType.values()) {
            if (stype.type.equalsIgnoreCase(type)) {
                return stype;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return this.type;
    }


}
