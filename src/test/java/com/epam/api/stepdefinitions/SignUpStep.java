package com.epam.api.stepdefinitions;

import com.epam.Models.SignUpUser;
import com.epam.configuration.ConfigurationManager;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class SignUpStep {
    private RequestSpecification request;
    private Response response;
    private ConfigurationManager cm = ConfigurationManager.getInstance();
    private final String BaseUrl = cm.getProperty("BaseURI");

    @Given("the API endpoint for user signup")
    public void theAPIEndpointForUserSignup() {
        RestAssured.baseURI = BaseUrl;
        request = given().header("Content-Type", "application/json");
    }

    @When("I send a POST request with the following data:")
    public void iSendAPOSTRequestWithTheFollowingData(DataTable dataTable) {
        Map<String, String> userData = dataTable.asMap(String.class, String.class);
        SignUpUser signUpUser = new SignUpUser();
        signUpUser.setFullName(userData.get("fullName"));
        signUpUser.setPassword(userData.get("password"));
        signUpUser.setEmail(userData.get("email"));
        signUpUser.setTarget(userData.get("target"));
        signUpUser.setPreferableActivity(userData.get("preferableActivity"));

        response = request.body(signUpUser).post("/signup");
    }

    @Then("I should receive a {int} status code")
    public void iShouldReceiveAStatusCode(int statusCode) {
        response.then().statusCode(statusCode);
    }

    @And("the response should contain Successfull message with username")
    public void theResponseShouldContainSuccessfullMessageWithUsername(DataTable dataTable) {
        Map<String, String> userData = dataTable.asMap(String.class, String.class);
        response.then()
                .body("data.role", equalTo("client"))
                .body("data.username", equalTo(userData.get("username")))
                .body("message", equalTo("Sign-up successful"));
    }

    @And("the response should contain an error message {string}")
    public void theResponseShouldContainAnErrorMessage(String errorMessage) {
        response.then().body("message", containsString(errorMessage));
    }

}
