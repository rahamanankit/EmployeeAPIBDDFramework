Feature: Validate Add Employee Feature 
@AllTests 
@AddEmployee 
Scenario: Verify if employee is being successfully added using AddEmployeeAPI (Positive Scenario) 
	Given Employee Payload in File "AddEmployee" 
	And Employee has an ID "false" , has data "true" with ID "0" 
	When User calls "addEmployeeAPI" with "POST" HTTP Request and has ID "false" 
	Then The API call is success with status code 200 
	And "status" in response body is "success" 
	And "message" in response body is "Successfully! Record has been added." 
@AllTests 
@AddEmployeeNegativeScenario 
Scenario: Verify if employee is not added with invalid data using AddEmployeeAPI (Negative Scenario) 
	Given Employee Payload in File "AddEmployeeInvalidData" 
	And Employee has an ID "false" , has data "true" with ID "0" 
	When User calls "addEmployeeAPI" with "POST" HTTP Request and has ID "false" 
	Then The API call is success with status code 400 
	And "status" in response body is "Bad Request" 
	
	
