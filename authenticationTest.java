package api;

import org.json.JSONObject;
import static org.junit.Assert.*;
import org.json.JSONException;
import org.junit.*;

// import hashing utils
// going to do this later


public class authenticationTest{
  
	String realHash;
	
	String realUsername = "AuthUser";
	String realPassword = "teamDefault482";
	
	@Before
	public void setUpRealHash(){
	  realHash = HMAC(realUsername, realPassword);
	}
	
	@test
	public void testValidCreds() throws Exception{
	  String test1 = HMAC(realUsername, realPassword);
	  assertEquals(realHash, test1);
	}
	
	@test
	public void testValidPassword() throws Exception{
	  String test2 = HMAC(realUsername, "TeamDefault482");
	  //assertEquals(realHash, test2);
	  assertFalse(realHash.equals(test2));
	}
	
	@test
	public void testValidUsername() throws Exception{
	  String test3 = HMAC("authUser", realPassword);
	  //assertEquals(realHash, test3);
	  assertFalse(realHash.equals(test3));
	}
	
	@test
	public void testValidCreds2() throws Exception{
	  String test4 = HMAC("AuthUser", "teamDefault482");
	  //assertEquals(realHash, test4);
	  assertFalse(realHash.equals(test4));
	}
	
}
