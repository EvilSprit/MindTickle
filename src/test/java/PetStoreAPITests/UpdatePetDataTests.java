package PetStoreAPITests;

import org.example.TestData.TestDataGenerator;
import org.example.Utils.PetUtils;
import org.example.base.APITestBase;
import org.example.models.petdata.PetData;
import org.testng.annotations.Test;

public class UpdatePetDataTests extends APITestBase {

    private final PetData fullPetData = TestDataGenerator.generateFullPetData();
    private final PetData modifiedPet = TestDataGenerator
            .generateFullPetDataWithPetName(fullPetData, "Navneet");

    @Test
    public void testUpdateFullPetData() {
        new PetUtils().createPetSuccessfully(fullPetData)
                .putPetSuccessfully(modifiedPet)
                .assertPetData(modifiedPet);
    }

    @Test
    public void testPutNewPet() {
        new PetUtils().putPetSuccessfully(fullPetData).assertPetData(fullPetData);
    }

    @Test
    public void testPutIncorrectJson() {
        new PetUtils().putBadRequest(TestDataGenerator.INCORRECT_JSON);
    }
}
