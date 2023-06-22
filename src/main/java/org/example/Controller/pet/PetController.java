package org.example.Controller.pet;

import io.restassured.response.Response;
import org.example.Constants.TestConstants;
import org.example.Utils.TestUtils;
import org.example.models.petdata.PetData;

import static io.restassured.RestAssured.given;

public class PetController {

    public Response getPet(String petId) {
        return given(TestUtils.requestSpecification)
                .when()
                .get(TestConstants.BASE_PATH_PET + "/" + petId);
    }

    public Response postPet(PetData petData) {
        return given(TestUtils.requestSpecification)
                .when()
                .body(TestUtils.getPetData(petData).toString())
                .post(TestConstants.BASE_PATH_PET);
    }

    public Response postPet(Object petData) {
        return given(TestUtils.requestSpecification)
                .when()
                .body(petData)
                .post(TestConstants.BASE_PATH_PET);
    }

    public Response putPet(PetData petData) {
        return given(TestUtils.requestSpecification)
                .when()
                .body(TestUtils.getPetData(petData).toString())
                .put(TestConstants.BASE_PATH_PET);
    }

    public Response putPet(Object petData) {
        return given(TestUtils.requestSpecification)
                .when()
                .body(petData)
                .put(TestConstants.BASE_PATH_PET);
    }
}
