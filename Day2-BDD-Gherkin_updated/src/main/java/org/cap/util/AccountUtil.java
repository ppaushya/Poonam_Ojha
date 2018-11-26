package org.cap.util;

public class AccountUtil {

	public static int generateAccountno() {
		return (int)(Math.random()/5)*1000;
	}
}
