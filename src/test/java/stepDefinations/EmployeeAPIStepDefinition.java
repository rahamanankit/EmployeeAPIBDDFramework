package stepDefinations;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.junit.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import resources.APIResources;
import resources.Utils;

public class EmployeeAPIStepDefinition extends Utils {
	RequestSpecification res;
	Response response;
	String payloadData;
	String resourceEndPointURL;

	@Given("Employee Payload in File {string}")
	public void employee_payload_in_file(String fileName) throws IOException {
		// Getting the payload from the JSON file
		payloadData = getPayloadData(fileName + ".json");
		System.out.println(payloadData);
	}

	@Given("Employee has an ID {string} , has data {string} with ID {string}")
	public void employee_has_an_id_has_data_with_id(String isIDPresent, String isPayloadPresent, String ID)
			throws IOException {
		if (isIDPresent.equalsIgnoreCase("true") && isPayloadPresent.equalsIgnoreCase("false")) {
			res = given().log().all().spec(requestSpecification()).pathParam("id", ID);
		} else if (isIDPresent.equalsIgnoreCase("true") && isPayloadPresent.equalsIgnoreCase("true")) {
			res = given().log().all().spec(requestSpecification()).body(payloadData).pathParam("id", ID);
		} else if (isIDPresent.equalsIgnoreCase("false") && isPayloadPresent.equalsIgnoreCase("true")) {
			res = given().log().all().spec(requestSpecification()).body(payloadData);
		} else {
			res = given().log().all().spec(requestSpecification());
		}
	}

	@Given("Employees are present in the application")
	public void employees_are_present_in_the_application() throws IOException {
		// Calling method to generate the request object
		employee_has_an_id_has_data_with_id("false", "false", "0");
		// Calling method to generate the response object
		user_calls_with_http_request("getEmployeeAPI", "GET", "false");
		// Checking if employees are already present in the application
		Assert.assertTrue(getJsonListValue(response, "data").size() > 0);
	}

	@When("User calls {string} with {string} HTTP Request and has ID {string}")
	public void user_calls_with_http_request(String APIName, String HTTPMethod, String isIDPresent) {
		if (isIDPresent.equalsIgnoreCase("true")) {
			resourceEndPointURL = APIResources.valueOf(APIName).getResource() + "/{id}";
		} else {
			resourceEndPointURL = APIResources.valueOf(APIName).getResource();
		}
		if (HTTPMethod.equalsIgnoreCase("GET")) {
			response = res.when().get(resourceEndPointURL);
		} else if (HTTPMethod.equalsIgnoreCase("POST")) {
			response = res.when().post(resourceEndPointURL);
		} else if (HTTPMethod.equalsIgnoreCase("PUT")) {
			response = res.when().put(resourceEndPointURL);
		} else if (HTTPMethod.equalsIgnoreCase("DELETE")) {
			response = res.when().delete(resourceEndPointURL);
		}
	}

	@Then("The API call is success with status code {int}")
	public void the_api_call_is_success_with_status_code(int statusCode) {
		Assert.assertEquals(response.statusCode(), statusCode);
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String JSONResponseKey, String JSONExpectedResponse) {
		// Extracting the value of the key from the response and asserting if it equals
		// expected value
		Assert.assertEquals(getJsonValue(response, JSONResponseKey), JSONExpectedResponse);

	}
}
