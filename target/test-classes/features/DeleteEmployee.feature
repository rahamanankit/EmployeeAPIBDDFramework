Feature: Validate Delete Employee Feature 

@AllTests 
@DeleteEmployee 
Scenario: Verify if employee is being successfully deleted using DeleteEmployeeAPI (Positive Scenario)
	Given Employee has an ID "true" , has data "false" with ID "1" 
	When User calls "deleteEmployeeAPI" with "DELETE" HTTP Request and has ID "true" 
	Then The API call is success with status code 200 
	And "status" in response body is "success" 
	And "message" in response body is "Successfully! Record has been deleted" 
