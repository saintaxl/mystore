/**
 * 
 */
package com.mycloud.constant;

/**
 * @author Shawn
 *
 */
public enum PaymentStatus {
	// 未付款
	UNPAYED("UNPAYED"), 
	
	// 付款中
	PAYING("PAYING"), 
	
	// 已付款
	PAYED("PAYED"), 
	
	;

    private final String status;

    private PaymentStatus(String status) {
        this.status = status;
    }

    public String status() {
        return status;
    }

    public static PaymentStatus getStatus(String status) {
        for (PaymentStatus type : PaymentStatus.values()) {
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
