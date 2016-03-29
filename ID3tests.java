package api;

import org.json.JSONObject;
import static org.junit.Assert.*;
import org.json.JSONException;
import org.junit.*;
import java.util.*;


public class ID3tests{
  
	
	
	Vector<Integer> list1 = {1, 2, 3, 4, 5};
	list<Integer> list2 = {5, 4, 3, 2, 1};
  
  @test
  public void vectorSumTest(){
    assertEquals(15, sum(list1));
  }
  
  @test
  public void listSumTest(){
    assertEquals(15, sum(list2));
  }
  
  @test
  public void hasCatTest(){
    // need internal attributes 
  }
	
	
	
}
