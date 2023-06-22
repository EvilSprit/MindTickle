package PetStoreAPITests;

import org.example.TestData.TestDataGenerator;
import org.example.Utils.PetUtils;
import org.example.base.APITestBase;
import org.example.models.petdata.PetData;
import org.testng.annotations.Test;

public class FetchPetDataTests extends APITestBase {

    private final PetData fullPetData = TestDataGenerator.generateFullPetData();
    private final String notFoundId = "-1";

    @Test
    public void testGetPet() {
        new PetUtils().createPetSuccessfully(fullPetData).assertPetData(fullPetData);
    }

    @Test
    public void testGetPetNotFound() {
        new PetUtils().getNotFoundPetById(notFoundId);
    }
}
