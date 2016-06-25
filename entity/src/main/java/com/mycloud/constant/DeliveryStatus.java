/**
 * 
 */
package com.mycloud.constant;

/**
 * @author Shawn
 *
 */
public enum DeliveryStatus {
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

    private DeliveryStatus(String status) {
        this.status = status;
    }

    public String status() {
        return status;
    }

    public static DeliveryStatus getStatus(String status) {
        for (DeliveryStatus type : DeliveryStatus.values()) {
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
