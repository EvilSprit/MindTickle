package org.example.APITests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.example.Constants.TestConstants;
import org.example.Utils.APITestBase;
import org.example.Utils.TestUtils;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

/**
 * User API test class.
 *
 * @author Navneet Anand
 */
public class UserAPITest extends APITestBase {

    /**
     * Test Create user with Arrays.
     */
    @Test(priority = 0)
    public void testCreateUserWithArray() {
        given()
                .contentType(ContentType.JSON)
                .body(TestUtils.getUserData().toString())
                .when()
                .post(TestConstants.BASE_PATH_CREATE_USER_WITH_ARRAY)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body(TestConstants.KEY_MESSAGE, equalTo("ok"));
    }

    /**
     * Test User Response presence.
     *
     * @param username - username is the unique identity of a customer
     */
    @Test(priority = 1, dataProvider = TestConstants.KEY_USERNAME)
    public void testUserResponse(String username) {
        Response response = RestAssured.get(TestConstants.BASE_PATH_GET_USER_DATA, username);
        response.then().statusCode(HttpStatus.SC_OK)
                .body(TestConstants.KEY_USERNAME, equalTo(username))
                .body(TestConstants.KEY_EMAIL, equalTo(
                        TestUtils.getUserDataBasedOnTheKey(username, TestConstants.KEY_EMAIL)));
    }

    /**
     * Test Update User Request
     *
     * @param updateUsername - UserName to be updated
     */
    @Test(priority = 2)
    @Parameters({TestConstants.PARAMETER_UPDATE_USERNAME})
    public void testUpdateUserRequest(@Optional(TestConstants.USERNAME_1) String updateUsername) {
        given()
                .contentType(ContentType.JSON)
                .body(TestUtils.updateUserDataRequestBody(updateUsername).toString())
                .when()
                .put(TestConstants.BASE_PATH_GET_USER_DATA, updateUsername)
                .then()
                .statusCode(HttpStatus.SC_OK);
        Response response = RestAssured.get(TestConstants.BASE_PATH_GET_USER_DATA, updateUsername);
        response.then().statusCode(HttpStatus.SC_OK).log().all()
                .body(TestConstants.KEY_USERNAME, equalTo(updateUsername))
                .body(TestConstants.KEY_FIRSTNAME, equalTo("Happy"))
                .body(TestConstants.KEY_LASTNAME, equalTo("Singh"))
                .body(TestConstants.KEY_EMAIL, equalTo("happy_singh@gmail.com"));
    }

    /**
     * Test Invalid User Request URL.
     */
    @Test(priority = 3)
    public void testInvalidUserRequest() {
        Response response = RestAssured.get(TestConstants.BASE_PATH_INVALID_USER);
        response.then().statusCode(HttpStatus.SC_NOT_FOUND);
    }

    /**
     * Test Invalid URL.
     */
    @Test(priority = 4)
    public void testInvalidURL() {
        Response response = RestAssured.get(TestConstants.BASE_PATH_INVALID_URL);
        response.then().statusCode(HttpStatus.SC_NOT_FOUND);
    }
}
