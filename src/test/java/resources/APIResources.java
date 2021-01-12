package resources;

//Enum is a special class for defining collection of constants or methods
public enum APIResources {
	addEmployeeAPI("api/v1/create"),
	getEmployeeAPI("api/v1/employees"),
	getEmployeeByIDAPI("api/v1/employee"),
	updateEmployeeAPI("api/v1/update"),
	deleteEmployeeAPI("/api/v1/delete");

	private String resource;

	APIResources(String resource) {
		// TODO Auto-generated constructor stub
		this.resource = resource;
	}

	public String getResource() {
		return resource;
	}

}
