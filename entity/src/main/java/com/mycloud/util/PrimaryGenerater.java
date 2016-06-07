/**
 * 
 */
package com.mycloud.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Shawn
 *
 */
public class PrimaryGenerater {


	private static PrimaryGenerater primaryGenerater = null;

	public static PrimaryGenerater getInstance() {
		if (primaryGenerater == null) {
			synchronized (PrimaryGenerater.class) {
				if (primaryGenerater == null) {
					primaryGenerater = new PrimaryGenerater();
				}
			}
		}
		return primaryGenerater;
	}

	public synchronized String generaterNextNumber() {
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHSSMM");
		return formatter.format(date);
	}
	
	public static void main(String[] args) {
		System.out.println(PrimaryGenerater.getInstance().generaterNextNumber());
    }

}
