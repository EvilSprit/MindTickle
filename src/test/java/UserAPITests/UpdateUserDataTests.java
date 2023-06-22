package UserAPITests;

import org.example.TestData.TestDataGenerator;
import org.example.Utils.UserUtils;
import org.example.base.APITestBase;
import org.example.models.userData.UserData;
import org.testng.annotations.Test;

public class UpdateUserDataTests extends APITestBase {

    private final UserData fullUserData = TestDataGenerator.generateFullUserData();

    private final UserData modifiedPet = TestDataGenerator
            .generateFullUserDataWithCustomName(fullUserData);

    @Test
    public void testUpdateFullUserData() {
        new UserUtils().createUserSuccessfully(fullUserData)
                .putPetSuccessfully(modifiedPet)
                .assertUserData(modifiedPet);
    }

    @Test
    public void testPutNewPet() {
        new UserUtils().putPetSuccessfully(fullUserData).assertUserData(fullUserData);
    }

    @Test
    public void testPutIncorrectJson() {
        new UserUtils().putBadRequest(TestDataGenerator.INCORRECT_JSON, "Navneet");
    }
}
