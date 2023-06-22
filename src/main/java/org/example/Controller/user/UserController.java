package org.example.Controller.user;

import io.restassured.response.Response;
import org.example.Constants.TestConstants;
import org.example.Utils.TestUtils;
import org.example.models.userData.UserData;

import java.util.List;

import static io.restassured.RestAssured.given;

public class UserController {

    public Response getUser(String username) {
        return given(TestUtils.requestSpecification)
                .when()
                .get(TestConstants.BASE_PATH_USER +"/"+ username);
    }

    public Response postUser(UserData userData) {
        return given(TestUtils.requestSpecification)
                .when()
                .body(TestUtils.getUserData(userData).toString())
                .post(TestConstants.BASE_PATH_USER);
    }

    public Response postUser(Object userData) {
        return given(TestUtils.requestSpecification)
                .when()
                .body(userData)
                .post(TestConstants.BASE_PATH_USER);
    }

    public Response postUser(List<UserData> userDataList) {
        return given(TestUtils.requestSpecification)
                .when()
                .body(TestUtils.getUserDataList(userDataList).toString())
                .post(TestConstants.BASE_PATH_USER + "/createWithArray");
    }

    public Response putUser(UserData userData) {
        return given(TestUtils.requestSpecification)
                .when()
                .body(TestUtils.getUserData(userData).toString())
                .put(TestConstants.BASE_PATH_USER + "/" + userData.getUsername());
    }

    public Response putUser(Object userData, String name) {
        return given(TestUtils.requestSpecification)
                .when()
                .body(userData)
                .put(TestConstants.BASE_PATH_USER);
    }
}
