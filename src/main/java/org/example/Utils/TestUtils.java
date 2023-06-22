package org.example.Utils;

import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.filter.log.LogDetail;
import org.example.models.apiResponse.APIResponse;
import org.example.models.petdata.CategoryData;
import org.example.models.petdata.PetData;
import org.example.Constants.TestConstants;
import org.example.models.petdata.TagData;
import org.example.models.userData.UserData;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;

import java.util.List;

/**
 * Test Utils Class.
 *
 * @author Navneet Anand
 */
public class TestUtils {

    public static final RequestSpecification requestSpecification = new RequestSpecBuilder()
            .log(LogDetail.ALL)
            .setContentType(ContentType.JSON)
            .addFilter(new AllureRestAssured())
            .addHeader(TestConstants.KEY_API, TestConstants.API_KEY_VALUE)
            .build();

    /**
     * Get Pet Data JSONObject.
     *
     * @param petData - PET Data Value class reference
     * @return - return JSONObject for the PET data
     */
    public static JSONObject getPetData(PetData petData) {
        JSONObject jsonObject = new JSONObject(petData);
        jsonObject.put(TestConstants.KEY_ID, petData.getId());
        jsonObject.put(TestConstants.KEY_CATEGORY, getCategoryJson(petData.getCategory()));
        jsonObject.put(TestConstants.KEY_NAME, petData.getName());
        jsonObject.put(TestConstants.KEY_PHOTO_URLS, getPhotoUrls(petData));
        jsonObject.put(TestConstants.KEY_TAGS, getTags(petData));
        jsonObject.put(TestConstants.KEY_STATUS, petData.getStatus());
        return jsonObject;
    }

    /**
     * Get Pet Data JSONObject.
     *
     * @return - return JSONObject for the PET data
     */
    public static JSONObject getUserData(UserData userData) {
        return new JSONObject(userData);
    }

    public static JSONArray getUserDataList(List<UserData> userDataList) {
        JSONArray jsonArray = new JSONArray();
        for (UserData userData : userDataList) {
            jsonArray.put(new JSONObject(userData));
        }
        return jsonArray;
    }

    /**
     * Generate Category JSON with PET Data.
     *
     * @param categoryData - category Data class
     * @return - return JSONObject with the PET DATA.
     */
    public static JSONObject getCategoryJson(CategoryData categoryData) {
        if (categoryData == null) {
            return null;
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(TestConstants.KEY_ID, categoryData.getId());
        jsonObject.put(TestConstants.KEY_NAME, categoryData.getName());
        return jsonObject;
    }

    /**
     * Generate PET Photo URLs List.
     *
     * @param petData - PET Data Value class reference
     * @return - return JSONArray with the PET PHOTO URLs
     */
    public static JSONArray getPhotoUrls(PetData petData) {
        if (petData.getPhotoUrls() == null || petData.getPhotoUrls().isEmpty()) {
            return null;
        }
        JSONArray jsonArray = new JSONArray();
        for (String string : petData.getPhotoUrls()) {
            jsonArray.put(string);
        }
        return jsonArray;
    }

    /**
     * Generate PET TAGs based in the values passed.
     *
     * @param petData - PET Data Value class reference
     * @return - return JSONArray with the PET Tags
     */
    public static JSONArray getTags(PetData petData) {
        if (petData.getTags() == null || petData.getTags().isEmpty()) {
            return null;
        }
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        for (TagData key : petData.getTags()) {
            jsonObject.put(TestConstants.KEY_ID, key.getId());
            jsonObject.put(TestConstants.KEY_NAME, key.getName());
        }
        jsonArray.put(jsonObject);
        return jsonArray;
    }

    public static APIResponse buildAPIResponse(String response) {
        JSONObject jsonObject = new JSONObject(response);
        int code = 0;
        String type = "";
        String message = "";
        for (String key : jsonObject.keySet()) {
            switch (key) {
                case "code":
                    code = Integer.parseInt(jsonObject.get(key).toString());
                case "type":
                    type = jsonObject.get(key).toString();
                case "message":
                    message = jsonObject.get(key).toString();
            }
        }
        return APIResponse.builder().code(code).type(type).message(message).build();
    }

    /**
     * Validate Response.
     */
    public static void validateResponse(JSONObject jsonObject1, JSONObject jsonObject2) {
        for (String key : jsonObject1.keySet()) {
            Assert.assertEquals(jsonObject1.get(key).toString(), jsonObject2.get(key).toString());
        }
    }

    @Step("Status Code {ExpectedStatus}")
    public static void assertStatusCode(int ExpectedStatus, Response response) {
        Assert.assertEquals(response.statusCode(), ExpectedStatus);
    }

    @Step("Assert Error Message {expectedMessage}")
    public static void assertErrorMessage(String expectedMessage, APIResponse apiResponse) {
        Assert.assertEquals(apiResponse.getMessage(), expectedMessage);
    }
}
