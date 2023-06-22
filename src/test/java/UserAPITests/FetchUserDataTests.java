package UserAPITests;

import org.example.TestData.TestDataGenerator;
import org.example.Utils.UserUtils;
import org.example.base.APITestBase;
import org.example.models.userData.UserData;
import org.testng.annotations.Test;

public class FetchUserDataTests extends APITestBase {

    private final UserData fullUserData = TestDataGenerator.generateFullUserData();
    private final String notFoundId = "-1";

    @Test
    public void testGetUser() {
        new UserUtils().createUserSuccessfully(fullUserData).assertUserData(fullUserData);
    }

    @Test
    public void testGetPetNotFound() {
        new UserUtils().getNotFoundUserByUsername(notFoundId);
    }
}
