package tests;

import io.cucumber.messages.internal.com.google.protobuf.Api;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.path.xml.XmlPath;
import io.restassured.path.xml.element.NodeChildren;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import javax.sql.rowset.spi.XmlReader;

import java.util.List;

import static io.restassured.RestAssured.given;
import static net.bytebuddy.matcher.ElementMatchers.anyOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;

import io.restassured.path.xml.config.XmlPathConfig;
import io.restassured.path.xml.element.Node;
import io.restassured.path.xml.element.NodeChildren;
import static io.restassured.path.xml.XmlPath.*;

public class APITests {

	
	public static RequestSpecification httpRequest;
	public static Response response;
  
    @Test
    public void AssertTest() throws Exception{

String response=given().header("Content-Type","text/xml").header("Connection","keep-alive")
               .header("User-Agent","PostmanRuntime/7.28.4")
                .when()
                .get("http://www.colourlovers.com/api/patterns")
                .then()
                .extract().body().asString();

XmlPath xml= new XmlPath(response);

List<String> numviews=xml.getList("patterns.pattern.numViews");

SoftAssert softAssertion= new SoftAssert();

System.out.println(numviews.size());
for(String num:numviews) {
	
	int num_views=Integer.parseInt(num); 
	
	System.out.println(num_views);
	softAssertion.assertTrue(num_views>4000);
	softAssertion.assertAll();
	
}
  
  
  
        
        

    }
    	
    /*	
    	RestAssured.baseURI = "https://www.colourlovers.com";
    	httpRequest = RestAssured.given();
    	response = httpRequest.request(Method.GET,"/api/patterns");
    	
    	String responseBody = response.getBody().asString();
		System.out.println("Response Body==>"+ responseBody);
		Assert.assertTrue(responseBody!=null);
    /*	
    	RestAssured.baseURI = "http://www.colourlovers.com";
   String res= 	 given().contentType("text/xml")
		
		.when()
			.get("/api/patterns")
			
		.then()
			.extract().body().asString();
    	 
    	 
    	 System.out.println(res);
			
    	
    	
    	/*
        String response =   given()
                .when().
                
                get(APIURL)
                .body().asString();
              

        System.out.println(response);
      /*  XmlPath xmlPath = new XmlPath(response.body().asString());
        List numViewList = xmlPath.getList("/patterns/pattern/numViews");

        System.out.println(numViewList + " " + numViewList.size());
 */
    }
