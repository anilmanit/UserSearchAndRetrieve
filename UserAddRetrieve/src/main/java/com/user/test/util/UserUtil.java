package com.user.test.util;

import java.util.Random;

public class UserUtil {

	public static boolean generateRandomBoolean(){
		
		  Random rd = new Random(); // creating Random object
	      return rd.nextBoolean(); 
	}
}
