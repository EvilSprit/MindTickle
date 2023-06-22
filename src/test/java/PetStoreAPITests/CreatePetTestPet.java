package PetStoreAPITests;

import org.example.TestData.TestDataGenerator;
import org.example.Utils.PetUtils;
import org.example.base.APITestBase;
import org.example.models.petdata.PetData;
import org.testng.annotations.Test;

public class CreatePetTestPet extends APITestBase {

    private final PetData minPetData = TestDataGenerator.generateMinPetData();
    private final PetData fullPetData = TestDataGenerator.generateFullPetData();

    @Test
    public void testPetCreationWithFullData() {
        new PetUtils().createPetSuccessfully(fullPetData);
    }

    @Test
    public void testPetCreationWithMinData() {
        new PetUtils().createPetSuccessfully(minPetData);
    }

    @Test
    public void testPetCreationWithInvalidData() {
        new PetUtils().postBadRequest(TestDataGenerator.INCORRECT_JSON);
    }
}
