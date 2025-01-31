package ApiTesting;


import io.restassured.RestAssured; 
import io.restassured.response.Response; 
import org.testng.Assert; 
import org.testng.annotations.Test; 

public class CoinDesk{ 
	@Test 
	
	public void coinGetAPI() { 
		
		// 1. Send the GET request
		Response response = RestAssured.get("https://api.coindesk.com/v1/bpi/currentprice.json"); 
		
		 
		//2 Verify the response contains
		// a. There are 3 BPIs
		//		i. USD
		//		ii. GBP
		//		iii. EUR
		
	
		String responseBody = response.getBody().asString(); 
				
		// Verifying that response body contains USD, GBP, and EUR 
		Assert.assertTrue(responseBody.contains("USD")); 
		Assert.assertTrue(responseBody.contains("GBP")); 
		Assert.assertTrue(responseBody.contains("EUR")); 
		
		//2 b. The GBP ‘description’ equals ‘British Pound Sterling’.
		String gbpData = response.jsonPath().getString("bpi.GBP.description"); 
		Assert.assertEquals(gbpData, "British Pound Sterling", "GBP description mismatch!"); 

		
	}
}

