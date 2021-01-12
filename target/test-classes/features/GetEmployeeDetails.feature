Feature: Validate Get Employee Feature 
@AllTests 
@GetEmployee 
Scenario: Verify if all employees are retrieved using GetEmployeeAPI (Positive Scenario) 
	Given Employees are present in the application 
	When User calls "getEmployeeAPI" with "GET" HTTP Request and has ID "false" 
	Then The API call is success with status code 200 
	And "status" in response body is "success" 
@AllTests
@GetEmployeeByID 
Scenario: Verify if an employee is retrieved using GetEmployeeAPI (Positive Scenario) 
	Given Employee has an ID "true" , has data "false" with ID "1" 
	When User calls "getEmployeeByIDAPI" with "GET" HTTP Request and has ID "true" 
	Then The API call is success with status code 200 
	And "status" in response body is "success" 
	And "message" in response body is "Successfully! Record has been fetched." 
	
@AllTests
@GetEmployeeNegativeScenario 
Scenario: Verify that an employee cannot be retrieved by passing a word in the ID (Negative Scenario) 
	Given Employee has an ID "true" , has data "false" with ID "abcd" 
	When User calls "getEmployeeByIDAPI" with "GET" HTTP Request and has ID "true" 
	Then The API call is success with status code 404 
	And "status" in response body is "Not Found" 
	
	
