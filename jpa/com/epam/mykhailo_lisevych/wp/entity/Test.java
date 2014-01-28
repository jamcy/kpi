package com.epam.mykhailo_lisevych.wp.entity;

import org.jboss.security.auth.spi.Util;

public class Test {
	public static void main(String[] args) {
		System.out.println(Util.createPasswordHash("MD5", Util.BASE16_ENCODING,
				"UTF-8", "admin", "admin"));
	}
}
