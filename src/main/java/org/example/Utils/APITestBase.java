package org.example.Utils;

import io.restassured.RestAssured;
import org.example.Constants.TestConstants;
import org.testng.TestNG;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;

import java.lang.reflect.Method;

/**
 * API Test Base.
 *
 * @author Navneet Anand
 */
public class APITestBase extends TestNG {

    /**
     * Set the Base URL Before the Suite start
     */
    @BeforeSuite
    public void suiteSetup() {
        RestAssured.baseURI = TestConstants.SERVICE_ENDPOINT;
    }

    /**
     * Print the method name before any test starts
     *
     * @param method - method object to be passed.
     */
    @BeforeMethod
    public void testSetup(Method method) {
        System.out.println("Method Name -> " + method.getName());
    }

    /**
     * Username data provider.
     *
     * @return - return object in 2D array
     */
    @DataProvider(name = "username")
    public Object[][] username() {
        return new Object[][]{
                {TestConstants.USERNAME_1},
                {TestConstants.USERNAME_2},
                {TestConstants.USERNAME_3},
                {TestConstants.USERNAME_4}
        };
    }
}
