package Tests;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import org.junit.Before;
import org.junit.Test;

public class TestBack {

   final static String url="http://demo.guru99.com/V4/sinkministatement.php?CUSTOMER_ID=68195&PASSWORD=1234!&Account_No=1";

   public static void main(String args[]) {
	   
	   GetLagunitas();
	   //getSize();
    
    

; }

   @Test
   public static void GetLagunitas()
   {   
   // Specify the base URL to the RESTful web service
   RestAssured.baseURI = "https://api.openbrewerydb.org/breweries/";
   
   // Get the RequestSpecification of the request that you want to sent
   // to the server. The server is specified by the BaseURI that we have
   // specified in the above step.
   RequestSpecification httpRequest = RestAssured.given().contentType("application/json").queryParam("query","lagunitas");
  
   
   // Make a request to the server by specifying the method Type and the method URL.
   // This will return the Response from the server. Store the response in a variable.
   Response response = httpRequest.request(Method.GET, "/autocomplete");
   List<String> jsonResponse = response.jsonPath().getList("$");
   List<String> listaIds= new ArrayList();
   
   for(int i=0; i< jsonResponse.size(); i++)
   {
	   System.out.println(i+ response.jsonPath().getList("name").get(i).toString()+ response.jsonPath().getList("id").get(i).toString());
	   
	   if (response.jsonPath().getList("name").get(i).toString().equals("Lagunitas Brewing Co") )
	   {
		   listaIds.add(response.jsonPath().getList("id").get(i).toString());
	   }
   }
     
   RequestSpecification httpRequest2 = RestAssured.given().contentType("application/json");
   
   for(int i=0; i< listaIds.size(); i++)
   {
   
   Response response2 = httpRequest.request(Method.GET, "/"+listaIds.get(i));
   
   	if (response2.jsonPath().getString("state").equals("California"))
   	{ 
   	   response2.then().assertThat()
      
      .body("id", equalTo(761))
      .body("name", equalTo("Lagunitas Brewing Co"))    	
      .body("street", equalTo("1280 N McDowell Blvd"))
      .body("phone", equalTo("7077694495"));
   	}
  
   }
   
   }
 


}