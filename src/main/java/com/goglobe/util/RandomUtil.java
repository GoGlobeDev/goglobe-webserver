package com.goglobe.util;

import java.util.Random;

public class RandomUtil {

	public static String randomNum(Integer number){
		String result = "";
		Random random = new Random();
		for(int i = 0 ; i < number ; i++){
			result += String.valueOf(random.nextInt(10));  
		}
		return result;
	}
	
	public static String randomChar(Integer number){
		String result = "";
		Random random = new Random();
		for(int i = 0 ; i < number ; i++){
			result += (char)(random.nextInt(26) + 65);  
		}
		return result;
	}
	
	public static String randomUppercase(Integer number){
		String result = "";
		Random random = new Random();
		for(int i = 0 ; i < number ; i++){
			result += (char)(random.nextInt(26) + 97);  
		}
		return result;
	}
	
	public static String randomCharBoth(Integer number){
		String result = "";
		Random random = new Random();
		for(int i = 0 ; i < number ; i++){
			int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;  
			result += (char)(random.nextInt(26) + temp);  
		}
		return result;
	}
	
	public static byte[] randomByte(Integer number){
		byte[] bytes = new byte[number];
		Random random = new Random();
		random.nextBytes(bytes);
		return bytes;
	}
	
	

}
