package org.example.Utils;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.example.Controller.user.UserController;
import org.example.models.userData.UserData;
import org.json.JSONObject;

import java.util.List;

public class UserUtils extends UserController {

    @Step("Create User Successfully {userData}")
    public UserUtils createUserSuccessfully(UserData userData) {
        Response response = postUser(userData);
        System.out.println(response.getBody().asString());
        TestUtils.assertStatusCode(HttpStatus.SC_OK, response);
        return this;
    }

    @Step("Create User Successfully {userDataList}")
    public UserUtils createUserSuccessfullyWithArray(List<UserData> userDataList) {
        Response response = postUser(userDataList);
        System.out.println(response.getBody().asString());
        TestUtils.assertStatusCode(HttpStatus.SC_OK, response);
        return this;
    }

    @Step("Post Bad Request {userData}")
    public UserUtils postBadRequest(UserData userData) {
        Response response = postUser(userData);
        TestUtils.assertStatusCode(HttpStatus.SC_BAD_REQUEST, response);
        return this;
    }

    public UserUtils postBadRequest(Object userData) {
        Response response = postUser(userData);
        TestUtils.assertStatusCode(HttpStatus.SC_BAD_REQUEST, response);
        return this;
    }

    @Step("Assert Pet Data {expectedPetData}")
    public UserUtils assertUserData(UserData expectedUserData) {
        JSONObject user = getUserByUsername(expectedUserData.getUsername());
        TestUtils.validateResponse(user, TestUtils.getUserData(expectedUserData));
        return this;
    }

    @Step("Get PetData by ID {petId}")
    public JSONObject getUserByUsername(String username) {
        Response response = getUser(username);
        TestUtils.assertStatusCode(HttpStatus.SC_OK, response);
        return new JSONObject(response.getBody().asString());
    }

    @Step("Get Not Found Pet By ID {petId}")
    public void getNotFoundUserByUsername(String username) {
        Response response = getUser(username);
        TestUtils.assertStatusCode(HttpStatus.SC_NOT_FOUND, response);
        TestUtils.assertErrorMessage("User not found",
                TestUtils.buildAPIResponse(response.getBody().asString()));
    }

    @Step("Put Pet Successfully {petData}")
    public UserUtils putPetSuccessfully(UserData userData) {
        Response response = putUser(userData);
        TestUtils.assertStatusCode(HttpStatus.SC_OK, response);
        assertUserData(userData);
        return this;
    }

    @Step("Put Bad Request {petData}")
    public UserUtils putBadRequest(Object userData, String username) {
        Response response = putUser(userData, username);
        TestUtils.assertStatusCode(HttpStatus.SC_METHOD_NOT_ALLOWED, response);
        return this;
    }
}
