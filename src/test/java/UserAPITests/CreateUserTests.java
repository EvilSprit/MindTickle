package UserAPITests;

import org.example.TestData.TestDataGenerator;
import org.example.Utils.UserUtils;
import org.example.base.APITestBase;
import org.example.models.userData.UserData;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class CreateUserTests extends APITestBase {

    private final UserData fullUserData = TestDataGenerator.generateFullUserData();
    private final List<UserData> fullUserDataList =
            Arrays.asList(TestDataGenerator.generateFullUserData(), TestDataGenerator.generateFullUserData());

    @Test
    public void testUserCreationWithFullData() {
        new UserUtils().createUserSuccessfully(fullUserData);
    }

    @Test
    public void testUserCreationWithFullDataArray() {
        new UserUtils().createUserSuccessfullyWithArray(fullUserDataList);
    }

    @Test
    public void testUserCreationWithInvalidData() {
        new UserUtils().postBadRequest(TestDataGenerator.INCORRECT_JSON);
    }
}
