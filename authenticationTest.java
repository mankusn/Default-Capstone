package test;

import static org.junit.Assert.*;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import org.junit.*;

// import hashing utils
// going to do this later


public class authTest{
  
	String realHash;
	
	String realUsername = "AuthUser";
	String realPassword = "teamDefault482";
	
	authentication AUTH = new authentication();
	
	@Before
	public void setUpRealHash() throws InvalidKeyException, NoSuchAlgorithmException{
	  realHash = AUTH.HMAC(realUsername, realPassword);
	}
	
	@Test
	public void testValidCreds() throws Exception{
	  String test1 = AUTH.HMAC(realUsername, realPassword);
	  assertEquals(realHash, test1);
	}
	
	@Test
	public void testValidPassword() throws Exception{
	  String test2 = AUTH.HMAC(realUsername, "TeamDefault482");
	  //assertEquals(realHash, test2);
	  assertFalse(realHash.equals(test2));
	}
	
	@Test
	public void testValidUsername() throws Exception{
	  String test3 = AUTH.HMAC("authUser", realPassword);
	  //assertEquals(realHash, test3);
	  assertFalse(realHash.equals(test3));
	}
	
	@Test
	public void testValidCreds2() throws Exception{
	  String test4 = AUTH.HMAC("AuthUser", "teamDefault482");
	  //assertEquals(realHash, test4);
	  assertEquals(realHash, test4);
	}
	
}
