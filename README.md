# EmployeeAPI BDD Test Framework
Behaviour Driven Rest API Framework implemented using Java, RestAssured and Cucumber on Maven Build Management Tool. The framework currently has 7 tests in total for testing all the CRUD operations using GET, POST, PUT and DELETE HTTP Methods.

## Features
1. Test the GetEmployee API using GET HTTP Request
2. Test the AddEmployee API using POST HTTP Request
3. Test the UpdateEmployee API using PUT HTTP Request
4. Test the DeleteEmployee API using DELETE HTTP Request

## Test Tags Supported
- @AddEmployee - To test the scenario for adding an employee 
- @AddEmployeeNegativeScenario - To test the negative scenario for adding an employee with invalid payload
- @DeleteEmployee - To test the scenario for deleting an employee
- @GetEmployee - To test the scenario for retrieving the details of all the employees
- @GetEmployeeByID - To test the scenario for retrieving the details of a particular employee by the employee ID
- @GetEmployeeNegativeScenario - To test the negative scenario for retrieving the details of an employee using invalid ID
- @UpdateEmployee - To test the scenario for updating the details of an existing employee
- @AllTests - To trigger all the scenarios at once

### Steps to setup the framework
- Download the code from the repository.
- Place the folder in your local directory
- Open command prompt from the same directory
- In the command line, type the following command
```console
 mvn test verify -Dcucumber.filter.tags="@AllTests"
```
For running other tags, replace the tag in the double quotes with one of the supported tags mentioned above.

## Generated Cucumber Reports
1. Navigate to Project Directory/target/cucumber-html-reports
2. Open the **overview-features.html** file to view the reports.

## Sample Generated Report
![Image of Sample Report](https://i.ibb.co/HgdNfKY/cucumber.png)

## Creating a Jenkins Pipeline
- Download the jenkins.war file
- Go to the Downloads Directory
- Open command prompt and type the following command
```console
 java -jar jenkins.war -httpPort=9090
```
- Open the browser once you get the message **Jenkins is fully up and running**
- Navigate to **localhost:9090**
- Setup the Jenkins Credentials
- Create a new job by choosing **Freestyle Project** and enter a Job Name
- Go to the General Tab
- Under the **Source Code Management** header, choose the **git** option
- Paste the current repository URL.
- In the **Build** section, click on **Add Build Step**
- Select the option **Invoke top level Maven-targets**
- In the **Goals** textbox, enter the maven command
```console
 test verify -Dcucumber.filter.tags="@"$tag""
```
- Click on Save button

## Parameterize the Jenkins Job
- Go to the General Tab
- Click the checkbox **This test is parameterized**
- Select the option **Choice Parameter**
- Enter the Name as the Name of the choice e.g. **tag**
- Enter all the **tags currently supported** as the **options**

## To trigger the job
- Click on **Build Now**
- Choose the tag as the option for running the tests.

**The tests must be triggered successfully now :smiley: **
