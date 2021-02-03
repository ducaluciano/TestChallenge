package Tests;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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

	private static final String textoBusqueda = "lagunitas";
	private static final String nameFiltro = "Lagunitas Brewing Co";
	private static final int id = 761;
	private static final String street = "1280 N McDowell Blvd";
    private static final String phone = "7077694495";
    private static final String state = "California";
    private static final String baseUrl = "https://api.openbrewerydb.org/breweries/";

   public  void main(String args[]) {
	    

; }

   @Test
   public void test () 
   {
	   Buscar(textoBusqueda);	   
   }
   
   public void Buscar(String textoBusqueda)
   {   
      RestAssured.baseURI = baseUrl;
      RequestSpecification httpRequest = RestAssured.given().contentType("application/json").queryParam("query",textoBusqueda);
      Response response = httpRequest.request(Method.GET, "/autocomplete");
      
      //guardo la respuesta
      List<String> jsonResponse = response.jsonPath().getList("$");
      assertNotNull("No se obtuvo respuesta",jsonResponse);
      
      //creo una lista para guardar los ids que compluan con la condicion
      List<String> listaIds = getIds(response, jsonResponse);
      //busca los objetos para esos ids y asserta
      checkByIds(listaIds);
      System.out.println("Pass!");
   }


   private static void checkByIds( List<String> listaIds) {
	   
	//RequestSpecification httpRequest2 = RestAssured.given().contentType("application/json");
	  Boolean hayResultado = false;
      for(int i=0; i< listaIds.size(); i++)
      {
    	  RestAssured.baseURI = baseUrl;
          RequestSpecification httpRequest = RestAssured.given().contentType("application/json");
          Response response = httpRequest.request(Method.GET, "/"+listaIds.get(i));
         
                  
          if (response.jsonPath().getString("state").equals(state))
    	  {     	
        	  System.out.println("if");
    		  response.then().assertThat()
    		  .body("id", equalTo(id))
    		  .body("name", equalTo(nameFiltro))    	
    		  .body("street", equalTo(street))
    		  .body("phone", equalTo(phone));
    		  hayResultado = true;
    	  } 
         
      }
      assertTrue("No se obtuvieron resultados para el estado: "+state, hayResultado);

   }

   	private static List<String> getIds(Response response, List<String> jsonResponse) {
   		
   	List<String> listaIds= new ArrayList();
   
      for(int i=0; i< jsonResponse.size(); i++)
      {
    	  
	   if (response.jsonPath().getList("name").get(i).toString().equals(nameFiltro) )
	   {
		   listaIds.add(response.jsonPath().getList("id").get(i).toString());
	   }
	   
      }
	
      assertNotNull("No se obtuvieron resultados para los ids proporcionados en la respuesta",listaIds);
      return listaIds;
}
 


}