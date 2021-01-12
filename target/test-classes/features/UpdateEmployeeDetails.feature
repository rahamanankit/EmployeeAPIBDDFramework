Feature: Validate Update Employee Feature 
@AllTests
@UpdateEmployee 
Scenario: Verify if employee is being successfully updated using UpdateEmployeeAPI (Positive Scenario)
	Given Employee Payload in File "UpdateEmployee" 
	And Employee has an ID "true" , has data "true" with ID "1" 
	When User calls "updateEmployeeAPI" with "PUT" HTTP Request and has ID "true" 
	Then The API call is success with status code 200 
