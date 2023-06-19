package org.example.Utils;

import org.example.Constants.PetData;
import org.example.Constants.TestConstants;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Test Utils Class.
 *
 * @author Navneet Anand
 */
public class TestUtils {

    /**
     * User Data list.
     */
    private static final List<Map<String, String>> LIST_OF_USER = List.of(
            new HashMap<>() {{
                put(TestConstants.KEY_ID, "168717073347");
                put(TestConstants.KEY_USERNAME, TestConstants.USERNAME_1);
                put(TestConstants.KEY_FIRSTNAME, "Navneet");
                put(TestConstants.KEY_LASTNAME, "Anand");
                put(TestConstants.KEY_EMAIL, "navneet@gmail.com");
                put(TestConstants.KEY_PASSWORD, TestConstants.DUMMY_PASSWORD);
                put(TestConstants.KEY_PHONE, TestConstants.DUMMY_CONTACT_NO);
                put(TestConstants.KEY_USER_STATUS, "0");
            }},
            new HashMap<>() {{
                put(TestConstants.KEY_ID, "168170733437");
                put(TestConstants.KEY_USERNAME, TestConstants.USERNAME_2);
                put(TestConstants.KEY_FIRSTNAME, "Aviral");
                put(TestConstants.KEY_LASTNAME, "Harsh");
                put(TestConstants.KEY_EMAIL, "navneet_anand@gmail.com");
                put(TestConstants.KEY_PASSWORD, TestConstants.DUMMY_PASSWORD);
                put(TestConstants.KEY_PHONE, TestConstants.DUMMY_CONTACT_NO);
                put(TestConstants.KEY_USER_STATUS, "0");
            }},
            new HashMap<>() {{
                put(TestConstants.KEY_ID, "168717073337");
                put(TestConstants.KEY_USERNAME, TestConstants.USERNAME_3);
                put(TestConstants.KEY_FIRSTNAME, "Sakshi");
                put(TestConstants.KEY_LASTNAME, "Prakash");
                put(TestConstants.KEY_EMAIL, "navneet_anand_123445@gmail.com");
                put(TestConstants.KEY_PASSWORD, TestConstants.DUMMY_PASSWORD);
                put(TestConstants.KEY_PHONE, TestConstants.DUMMY_CONTACT_NO);
                put(TestConstants.KEY_USER_STATUS, "0");
            }},
            new HashMap<>() {{
                put(TestConstants.KEY_ID, "168710733437");
                put(TestConstants.KEY_USERNAME, TestConstants.USERNAME_4);
                put(TestConstants.KEY_FIRSTNAME, "Ishita");
                put(TestConstants.KEY_LASTNAME, "Raj");
                put(TestConstants.KEY_EMAIL, "navneet_anand1244@gmail.com");
                put(TestConstants.KEY_PASSWORD, TestConstants.DUMMY_PASSWORD);
                put(TestConstants.KEY_PHONE, TestConstants.DUMMY_CONTACT_NO);
                put(TestConstants.KEY_USER_STATUS, "0");
            }}
    );

    /**
     * Get User Data Based on the Key passed.
     *
     * @param username - UserName from the date to be fetched
     * @param key      - data to be returned based on the key values
     * @return - return found data as a string
     */
    public static String getUserDataBasedOnTheKey(String username, String key) {
        for (Map<String, String> stringMap : LIST_OF_USER) {
            if (stringMap.get(TestConstants.KEY_USERNAME).equals(username)) {
                return stringMap.get(key);
            }
        }
        throw new IllegalArgumentException("Invalid Username : " + username);
    }

    /**
     * Get User Data as JSONArray.
     *
     * @return - return user data as JSONArray
     */
    public static JSONArray getUserData() {
        JSONArray jsonArray = new JSONArray();
        for (Map<String, String> stringMap : LIST_OF_USER) {
            JSONObject jsonObject = new JSONObject();
            for (String key : stringMap.keySet()) {
                jsonObject.put(key, stringMap.get(key));
            }
            jsonArray.put(jsonObject);
        }
        return jsonArray;
    }

    /**
     * Update User JSONObject.
     * @param username - username to be updated
     * @return - return JSONObject created based on username
     */
    public static JSONObject updateUserDataRequestBody(String username) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(TestConstants.KEY_ID, getUserDataBasedOnTheKey(username, TestConstants.KEY_ID));
        jsonObject.put(TestConstants.KEY_USERNAME, username);
        jsonObject.put(TestConstants.KEY_FIRSTNAME, "Happy");
        jsonObject.put(TestConstants.KEY_LASTNAME, "Singh");
        jsonObject.put(TestConstants.KEY_EMAIL, "happy_singh@gmail.com");
        jsonObject.put(TestConstants.KEY_PASSWORD, TestConstants.DUMMY_PASSWORD);
        jsonObject.put(TestConstants.KEY_PHONE, TestConstants.DUMMY_CONTACT_NO);
        jsonObject.put(TestConstants.KEY_USER_STATUS, "0");
        return jsonObject;
    }

    /**
     * Get Pet Data JSONObject.
     *
     * @param petData - PET Data Value class reference
     * @return - return JSONObject for the PET data
     */
    public static JSONObject getPetData(PetData petData) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(TestConstants.KEY_ID, petData.getId());
        jsonObject.put(TestConstants.KEY_CATEGORY, getCategoryJson(petData));
        jsonObject.put(TestConstants.KEY_NAME, petData.getPetName());
        jsonObject.put(TestConstants.KEY_PHOTO_URLS, getPhotoUrls(petData));
        jsonObject.put(TestConstants.KEY_TAGS, getTags(petData));
        jsonObject.put(TestConstants.KEY_STATUS, petData.getStatus());
        return jsonObject;
    }

    /**
     * Generate Category JSON with PET Data.
     *
     * @param petData - PET Data Value class reference
     * @return - return JSONObject with the PET DATA.
     */
    public static JSONObject getCategoryJson(PetData petData) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(TestConstants.KEY_ID, petData.getCategoryID());
        jsonObject.put(TestConstants.KEY_NAME, petData.getCategoryName());
        return jsonObject;
    }

    /**
     * Generate PET Photo URLs List.
     *
     * @param petData - PET Data Value class reference
     * @return - return JSONArray with the PET PHOTO URLs
     */
    public static JSONArray getPhotoUrls(PetData petData) {
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
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        for (String key : petData.getTags().keySet()) {
            jsonObject.put(TestConstants.KEY_ID, key);
            jsonObject.put(TestConstants.KEY_NAME, petData.getTags().get(key));
        }
        jsonArray.put(jsonObject);
        return jsonArray;
    }

    /**
     * Validate Response from PET.
     *
     * @param jsonObject - JSON Object to be validated
     * @param key        - Key to be validated
     * @param petData    - PET Data Class Reference
     * @return - return true if Expected match with actual
     */
    public static boolean validateResponse(JSONObject jsonObject, String key, PetData petData) {
        return switch (key) {
            case TestConstants.KEY_ID -> jsonObject.get(key).toString().equals(petData.getId());
            case TestConstants.KEY_NAME -> jsonObject.get(key).toString().equals(petData.getPetName());
            case TestConstants.KEY_STATUS -> jsonObject.get(key).toString().equals(petData.getStatus());
            default -> true;
        };
    }
}
