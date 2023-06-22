package org.example.base;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.example.Constants.TestConstants;
import org.testng.TestNG;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.lang.reflect.Method;

public class APITestBase extends TestNG {

    /**
     * Set the Base URL Before the Class start
     */
    @BeforeSuite
    public void suiteSetup() {
        RestAssured.defaultParser = Parser.JSON;
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
}
