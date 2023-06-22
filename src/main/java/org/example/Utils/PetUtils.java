package org.example.Utils;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.example.Controller.pet.PetController;
import org.example.models.petdata.PetData;
import org.json.JSONObject;

public class PetUtils extends PetController {

    @Step("Create Pet Successfully {petData}")
    public PetUtils createPetSuccessfully(PetData petData) {
        Response response = postPet(petData);
        System.out.println(response.getBody().asString());
        TestUtils.assertStatusCode(HttpStatus.SC_OK, response);
        return this;
    }

    @Step("Post Bad Request {petData}")
    public PetUtils postBadRequest(PetData petData) {
        Response response = postPet(petData);
        TestUtils.assertStatusCode(HttpStatus.SC_BAD_REQUEST, response);
        return this;
    }

    public void postBadRequest(Object petData) {
        Response response = postPet(petData);
        TestUtils.assertStatusCode(HttpStatus.SC_BAD_REQUEST, response);
    }

    @Step("Get PetData by ID {petId}")
    public JSONObject getPetById(String petId) {
        Response response = getPet(petId);
        TestUtils.assertStatusCode(HttpStatus.SC_OK, response);
        return new JSONObject(response.getBody().asString());
    }

    @Step("Get Not Found Pet By ID {petId}")
    public void getNotFoundPetById(String petId) {
        Response response = getPet(petId);
        TestUtils.assertStatusCode(HttpStatus.SC_NOT_FOUND, response);
        TestUtils.assertErrorMessage("Pet not found",
                TestUtils.buildAPIResponse(response.getBody().asString()));
    }

    @Step("Assert Pet Data {expectedPetData}")
    public void assertPetData(PetData expectedPetData) {
        JSONObject pet = getPetById(expectedPetData.getId().toString()); // response from server
        TestUtils.validateResponse(pet, TestUtils.getPetData(expectedPetData)); // compare response it with the expected
    }

    @Step("Put Pet Successfully {petData}")
    public PetUtils putPetSuccessfully(PetData petData) {
        Response response = putPet(petData);
        TestUtils.assertStatusCode(HttpStatus.SC_OK, response);
        assertPetData(petData);
        return this;
    }

    @Step("Put Bad Request {petData}")
    public PetUtils putBadRequest(PetData petData) {
        Response response = putPet(petData);
        TestUtils.assertStatusCode(HttpStatus.SC_BAD_REQUEST, response);
        return this;
    }

    @Step("Put Bad Request {petData}")
    public void putBadRequest(Object petData) {
        Response response = putPet(petData);
        TestUtils.assertStatusCode(HttpStatus.SC_BAD_REQUEST, response);
    }
}
