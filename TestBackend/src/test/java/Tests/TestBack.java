package Tests;
import static io.restassured.RestAssured.*;

import java.util.ArrayList;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class TestBack {

   final static String url="http://demo.guru99.com/V4/sinkministatement.php?CUSTOMER_ID=68195&PASSWORD=1234!&Account_No=1";

   public static void main(String args[]) {

	   
     getResponseBody();
    
     getResponseStatus();
     System.out.println("The dwadwadwa status is ");

; }

   //This will fetch the response body as is and log it. given and when are optional here
   public static void getResponseBody(){
	
	   ArrayList<String> response = given()
  //.queryParam("CUSTOMER_ID","68195")
         //      .queryParam("PASSWORD","1234!")
               .queryParam("query","lagunitas") 
               .when().get(" https://api.openbrewerydb.org/breweries/autocomplete").then()
               .contentType(ContentType.JSON).extract().
               path("name");
               System.out.println ("id list" + response);
   }

public static void getResponseStatus(){
   int statusCode= given().queryParam("CUSTOMER_ID","68195")
           .queryParam("PASSWORD","1234!")
           .queryParam("Account_No","1")
           .when().get("http://demo.guru99.com/V4/sinkministatement.php").getStatusCode();
   System.out.println("The response status is "+statusCode);

   given().when().get(url).then().assertThat();
}

}