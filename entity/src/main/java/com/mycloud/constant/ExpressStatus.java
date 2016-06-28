/**
 * 
 */
package com.mycloud.constant;

/**
 * @author Shawn
 *
 */
public enum ExpressStatus {
	// 未发货
	UNSHIPPED("UNSHIPPED"), 
	
	// 已发货
	SHIPPED("SHIPPED"), 
	
	// 已收货
	RECEIVED("RECEIVED"), 
	
	// 备货中
	PREPARING("PREPARING"), 
	
	;

    private final String status;

    private ExpressStatus(String status) {
        this.status = status;
    }

    public String status() {
        return status;
    }

    public static ExpressStatus getStatus(String status) {
        for (ExpressStatus type : ExpressStatus.values()) {
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
