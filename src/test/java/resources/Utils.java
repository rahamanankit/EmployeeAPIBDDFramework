package resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {
	// So that the same req object is shared across multiple data
	public static RequestSpecification req;

	public io.restassured.specification.RequestSpecification requestSpecification() throws IOException {
		//So that the log file is not created multiple times and so no overwrite happens
		if (req == null) {
			PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
			// Create the RequestSpecBuilder Object
			req = new RequestSpecBuilder().setBaseUri(getGlobalVariableValue("baseUrl"))
					.addFilter(RequestLoggingFilter.logRequestTo(log))
					.addFilter(ResponseLoggingFilter.logResponseTo(log)).setContentType(ContentType.JSON).build();
		}
		return req;
	}

	public String getGlobalVariableValue(String propertyName) throws IOException {
		// Get the Project Directory
		String projDir = System.getProperty("user.dir");
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(projDir + "\\src\\test\\java\\resources\\global.properties");
		prop.load(fis);
		// Return the value of the property name
		return prop.getProperty(propertyName);

	}
	public String getJsonValue(Response response, String key) {
		//Creating a JsonPath object from the response string
		JsonPath js = new JsonPath(response.asString());
		//Returning the value of the key as a string
		return js.get(key).toString();
	}
	public List<Object> getJsonListValue(Response response, String key) {
		//Creating a JsonPath object from the response string
		JsonPath js = new JsonPath(response.asString());
		//Returning the value of the key as a string
		return js.getList(key);
	}
	public String getPayloadData(String fileName) throws IOException {
		//Get the current project directory
		String proj = System.getProperty("user.dir");
		//Return the payload data in string format
		return new String(Files.readAllBytes(Paths.get(proj + "\\src\\test\\java\\resources\\"+fileName)));
		
	}
}
