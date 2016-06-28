/**
 * 
 */
package com.mycloud.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;

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
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		String format = formatter.format(date);
		String substring = StringUtils.substring(format, 0, 14)+ RandomStringUtils.randomNumeric(5);
		return substring;
	}
	
	public static void main(String[] args) {
		System.out.println(PrimaryGenerater.getInstance().generaterNextNumber());
    }

}
