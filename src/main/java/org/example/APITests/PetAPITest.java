package org.example.APITests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.commons.lang3.time.StopWatch;
import org.apache.http.HttpStatus;
import org.example.Constants.PetData;
import org.example.Constants.TestConstants;
import org.example.Utils.APITestBase;
import org.example.Utils.TestUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class PetAPITest extends APITestBase {

    static PetData petData;

    /**
     * Test Add New Pet Request.
     */
    @Test(priority = 0)
    public void testAddANewPet() {
        petData = PetData.builder()
                .petName(TestConstants.DUMMY_PET_NAME)
                .id(TestConstants.DUMMY_ID)
                .categoryID(TestConstants.DUMMY_CATEGORY_ID)
                .categoryName(TestConstants.DUMMY_CATEGORY_NAME)
                .photoUrls(List.of(
                        TestConstants.DUMMY_PHOTO_1,
                        TestConstants.DUMMY_PHOTO_2,
                        TestConstants.DUMMY_PHOTO_3))
                .tags(Map.of(TestConstants.DUMMY_TAG_ID, TestConstants.DUMMY_TAG_VALUE))
                .status(TestConstants.VALUE_PET_STATUS_AVAILABLE)
                .build();
        given()
                .contentType(ContentType.JSON)
                .body(TestUtils.getPetData(petData).toString())
                .when()
                .post(TestConstants.BASE_PATH_PET)
                .then()
                .statusCode(HttpStatus.SC_OK);
    }

    /**
     * Test Pet Status and validate newly created pet is present or not.
     *
     * @param petStatus - pet status.
     */
    @Test(priority = 1)
    @Parameters({TestConstants.KEY_USER_STATUS})
    public void testPetStatus(@Optional(TestConstants.VALUE_PET_STATUS_AVAILABLE) String petStatus) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Response response = RestAssured.get(TestConstants.BASE_PATH_FOR_PET_STATUS, petStatus);
        String body = response.getBody().asString();
        JSONArray jsonArr = new JSONArray(body);
        for (int i = 0; i < jsonArr.length(); i++) {
            JSONObject jsonObj = jsonArr.getJSONObject(i);
            if (jsonObj.get(TestConstants.KEY_ID).equals(Integer.valueOf(TestConstants.DUMMY_ID))) {
                for (String key : jsonObj.keySet()) {
                    Assert.assertTrue(TestUtils.validateResponse(jsonObj, key, petData));
                }
            }
        }
        stopWatch.stop();
        System.out.println("Response Time " + stopWatch.getTime()); // calculate latency
    }

    /**
     * Test Update pet details.
     */
    @Test(priority = 2)
    public void testUpdatePetDetails() {
        petData = PetData.builder()
                .petName(TestConstants.DUMMY_PET_NAME)
                .id(TestConstants.DUMMY_ID)
                .categoryID(TestConstants.DUMMY_CATEGORY_ID)
                .categoryName(TestConstants.DUMMY_CATEGORY_NAME)
                .photoUrls(List.of(
                        TestConstants.DUMMY_PHOTO_1,
                        TestConstants.DUMMY_PHOTO_2,
                        TestConstants.DUMMY_PHOTO_3))
                .tags(Map.of(TestConstants.DUMMY_TAG_ID, TestConstants.DUMMY_TAG_VALUE))
                .status(TestConstants.VALUE_PET_STATUS_PENDING)
                .build();
        given()
                .contentType(ContentType.JSON)
                .body(TestUtils.getPetData(petData).toString())
                .put(TestConstants.BASE_PATH_PET)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .log()
                .all();
    }

    /**
     * Test Pet Status After Update.
     *
     * @param petStatus - new petStatus to be checked.
     */
    @Test(priority = 3)
    @Parameters({TestConstants.PARAMETER_PET_STATUS})
    public void testPetStatusAfterUpdate(@Optional(TestConstants.VALUE_PET_STATUS_PENDING) String petStatus) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Response response = RestAssured.get(TestConstants.BASE_PATH_FOR_PET_STATUS, petStatus);
        String body = response.getBody().asString();
        System.out.println(body);
        JSONArray jsonArr = new JSONArray(body);
        for (int i = 0; i < jsonArr.length(); i++) {
            JSONObject jsonObj = jsonArr.getJSONObject(i);
            if (jsonObj.get(TestConstants.KEY_ID).equals(Integer.valueOf(TestConstants.DUMMY_ID))) {
                for (String key : jsonObj.keySet()) {
                    Assert.assertTrue(TestUtils.validateResponse(jsonObj, key, petData));
                }
            }
        }
        stopWatch.stop();
        System.out.println("Response Time " + stopWatch.getTime()); // calculate latency
    }
}
