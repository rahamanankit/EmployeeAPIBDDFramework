package stepDefinations;

import java.io.IOException;

import org.junit.Assert;

import io.cucumber.java.Before;

public class GetEmployeeHook {
	@Before("@GetEmployee")
	public void beforeScenario() throws IOException {
		EmployeeAPIStepDefinition e = new EmployeeAPIStepDefinition();
		e.employee_payload_in_file("AddEmployee");
		e.employee_has_an_id_has_data_with_id("false", "true", "0");
		e.user_calls_with_http_request("addEmployeeAPI", "POST", "false");
		Assert.assertEquals(e.response.statusCode(), 200);
	}
}
