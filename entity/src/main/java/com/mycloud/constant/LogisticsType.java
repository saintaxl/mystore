/**
 * 
 */
package com.mycloud.constant;

/**
 * @author Shawn
 *
 */
public enum LogisticsType {
	
	DELIVERY("DELIVERY"), 
	
	Express("Express"), 
	
	;

    private final String type;

    private LogisticsType(String type) {
        this.type = type;
    }

    public String type() {
        return type;
    }

    public static LogisticsType getStatus(String type) {
        for (LogisticsType stype : LogisticsType.values()) {
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
