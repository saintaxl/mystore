/**
 * 
 */
package com.mycloud.constant;

/**
 * @author Shawn
 *
 */
public enum SettlementStatus {
	// 未付款
	UNPAYED("UNPAYED"), 
	
	// 付款中
	PAYING("PAYING"), 
	
	// 已付款
	PAYED("PAYED"), 
	
	;

    private final String status;

    private SettlementStatus(String status) {
        this.status = status;
    }

    public String status() {
        return status;
    }

    public static SettlementStatus getStatus(String status) {
        for (SettlementStatus type : SettlementStatus.values()) {
            if (type.status.equalsIgnoreCase(status)) {
                return type;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return this.status;
    }


}
