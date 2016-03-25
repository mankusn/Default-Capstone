package apitest;

import java.security.InvalidKeyException;

/*TODO: 1) Get water data
		2) Integrate with database
		3) Get boat data?
*/
//Example program to test retrieving temperature from the forecast.io API

import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Base64.Encoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class Apitest{
	
	// -- -- -- -- -- - -- - -- -- -- -- - - - Security utils


        //convert byte to hex format
	public static String strConv(byte[] byteData){
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i < byteData.length; i++){
			sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
		}

		return sb.toString();	
	}
	
	public static String encryptRSA(String input) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException{
		KeyGenerator keyGenerator = KeyGenerator.getInstance("Blowfish");
		//KeyGenerator.getInstance
	    keyGenerator.init(128);
	    Key blowfishKey = keyGenerator.generateKey();
	
	    KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
	    keyPairGenerator.initialize(1024);
	    KeyPair keyPair = keyPairGenerator.genKeyPair();
	    
	
	    Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
	    
	    
	    cipher.init(Cipher.ENCRYPT_MODE, keyPair.getPublic());
	    
	    System.out.println("public key");
	    System.out.println(keyPair.getPublic());
	    System.out.println("private key");
	    System.out.println(keyPair.getPrivate());
	
	    byte[] blowfishKeyBytes = blowfishKey.getEncoded();
	    //System.out.println(" part1 ");
	    //System.out.println(new String(blowfishKeyBytes));
	    
	    return new String(blowfishKeyBytes);
	    
	}
	
	public String decryptRSA(String input){
		
		
		return "";
	}
	
	//http://www.java2s.com/Tutorial/Java/0490__Security/AnexampleofusingRSAtoencryptasingleasymmetrickey.htm
	public static void rsaTest(String args) throws Exception {
		
	    KeyGenerator keyGenerator = KeyGenerator.getInstance("Blowfish");
	    keyGenerator.init(128);
	    Key blowfishKey = keyGenerator.generateKey();
	
	    KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
	    keyPairGenerator.initialize(1024);
	    KeyPair keyPair = keyPairGenerator.genKeyPair();
	    
	
	    Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
	    cipher.init(Cipher.ENCRYPT_MODE, keyPair.getPublic());
	    
	    System.out.println("public key");
	    System.out.println(keyPair.getPublic());
	    System.out.println("private key");
	    System.out.println(keyPair.getPrivate());
	
	    byte[] blowfishKeyBytes = blowfishKey.getEncoded();
	    System.out.println(" part1 ");
	    System.out.println(new String(blowfishKeyBytes));
	    System.out.println(" in str ");
	    System.out.println(strConv(blowfishKeyBytes));
	    
	    // convert byte array into string and store into DB
	    byte[] cipherText = cipher.doFinal(blowfishKeyBytes);
	    
	    System.out.println(" part two ");
	    System.out.println(new String(cipherText));
	    System.out.println(" string");
	    System.out.println(strConv(cipherText));
	    
	    
	    cipher.init(Cipher.DECRYPT_MODE, keyPair.getPrivate());
	
	    byte[] decryptedKeyBytes = cipher.doFinal(cipherText);
	    System.out.println(" decrypted bytes ");
	    System.out.println(new String(decryptedKeyBytes));
	    System.out.println(" string ");
	    System.out.println(strConv(decryptedKeyBytes));
	    SecretKey newBlowfishKey = new SecretKeySpec(decryptedKeyBytes, "Blowfish");
	}
	
	public static String complHash(String inp1, String inp2) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException{
		
		//System.out.println("input 1:: " + inp1);
		//System.out.println("input 2:: " + inp2);
		
		String encoding = encryptRSA(inp1);
		//System.out.println("salt :: " + encoding);
		
		String sHb = inp2 + encoding;
		
		String resHash = hashPasscode(sHb);
		
		//System.out.println("result:: " + resHash);
		
		
		return resHash;
	}
	
	
	// https://crackstation.net/hashing-security.htm#salt
	
	public static String hashPasscode(String input) throws NoSuchAlgorithmException{
		
		// source: http://www.mkyong.com/java/java-sha-hashing-example/
		MessageDigest md = MessageDigest.getInstance("SHA-512");
		md.update(input.getBytes());
		
		byte byteData[] = md.digest();
		
		String a = new String(byteData);
		//System.out.println(" actual hash? :: " + a);
		
		//convert byte to hex format
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i < byteData.length; i++){
			sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
		}
		
		//System.out.println("Hex format of encrypted password: " + sb.toString());
		
		return sb.toString();
	}
	
	
	public static String HMAC(String inp1, String inp2) throws NoSuchAlgorithmException, InvalidKeyException{
		Mac sha512_HMAC = Mac.getInstance("HmacSHA512");
		SecretKeySpec secret_key = new SecretKeySpec(inp1.getBytes(), "HmacSHA512");
		sha512_HMAC.init(secret_key);
		Encoder asdf = Base64.getEncoder();
		String res = asdf.encodeToString(sha512_HMAC.doFinal(inp2.getBytes()));
		//String hash = Base64.encodeBase64String(sha512_HMAC.doFinal(inp2.getBytes()));
		
		
		return res;
	}
	
	// -- -- -- -- -- - - -- -- -- -- - - - -- End security utils

	
	
	public static void main(String args[]) throws Exception{
		//Currently retrieves the last week of temperatures
		//Uses American units (Fahrenheit, MPH)
		//System.out.println("NEXT WEEK'S FORECAST\n--------------------");
		//nextWeeksForecast();
		//System.out.println("LAST WEEK'S WEATHER\n-------------------");
		//lastWeeksForecast();
		
		
		//System.out.println(b);
		
		
		// username = salt  for hash
		// password = hashed and stored in DB
		
		String test = "AuthUser";
		String pass = "teamDefault482";
		
		String enc = HMAC(test, pass);
		String enc2 = HMAC(test, "TeamDefault482");
		String enc3 = HMAC("authUser", pass);
		String enc4 = HMAC("AuthUser", "teamDefault482");
		
		System.out.println(enc + '\n' + enc2 + '\n' + enc3 + '\n' + enc4);
		
		if(enc.equals(enc2)){
			System.out.println(" test 1 failed. invalid authentication.  ");
			
		}
		else System.out.println(" test 1 passed. failed to authenticate. ");
		
		if(enc.equals(enc3)){
			System.out.println(" test 2 failed. invalid authentication. ");
		}
		else System.out.println(" test 2 passed. failed to authenticate. ");
		
		if(enc.equals(enc4)){
			System.out.println(" test 3 passed. authentication successful. ");
		}
		else System.out.println(" test 3 failed. failed to authenticate. ");
		
		/*String res = complHash(test, pass);
		
		System.out.println("stored hash:: " + res);
		
		String testtwo = "authUser";
		String res1 = complHash(testtwo, pass);
		System.out.println("              " + res1);
		
		if(! res.equals(res1)){
			System.out.println("hashes do not equal. test passed ");
		}
		else System.out.println("test failed 1 ");
		
		String passtwo = "TeamDefault482";
		String res2 = complHash(test, passtwo);
		System.out.println("              " + res2);
		
		if(! res.equals(res2)){
			System.out.println("hashes do not equal. test passed. ");
		}
		else System.out.println("test failed 2 ");
		
		String res3 = complHash(test, pass);
		
		System.out.println(res);
		System.out.println(res3);
		
		if(! res.equals(res3)){
			System.out.println("hashes do not equal. test failed. ");
		}
		else System.out.println("test passed 3 ");
		/*
		System.out.println("My password is... \n teamDefault482");
		
		String encryptedPassword = hashPasscode("teamDefault482");
		
		System.out.println("checking orig password to encrypted... ");
		
		String plaintext = "teamDefault482";
		
		if(hashPasscode(plaintext).equals( encryptedPassword)){
			System.out.println("encrypted password hash matches plaintext hash");
		}
		else{
			System.out.println("test failed.");
		}
		
		
		rsaTest("hello");
		*/
	}
}
