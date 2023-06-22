package org.example.TestData;

import org.apache.commons.lang3.RandomStringUtils;
import org.example.models.petdata.CategoryData;
import org.example.models.petdata.PetData;
import org.example.models.petdata.Status;
import org.example.models.petdata.TagData;
import org.example.models.userData.UserData;

import java.util.Arrays;
import java.util.Random;

public class TestDataGenerator {

    public static final String INCORRECT_JSON = "{ \"id\": incorrectValue}";

    public static PetData generateFullPetData() {
        return PetData.builder()
                .id(getRandomInt())
                .name(getRandomString())
                .photoUrls(Arrays.asList(getRandomString(), getRandomString()))
                .category(CategoryData.builder().id(getRandomInt()).name(getRandomString()).build())
                .tags(Arrays.asList(TagData.builder().id(getRandomInt()).name(getRandomString()).build(),
                        TagData.builder().id(getRandomInt()).name(getRandomString()).build()))
                .status(Status.available)
                .build();
    }

    public static PetData generateFullPetDataWithPetName(PetData petData, String name) {
        return PetData.builder()
                .id(petData.getId())
                .name(name)
                .photoUrls(petData.getPhotoUrls())
                .category(petData.getCategory())
                .tags(petData.getTags())
                .status(petData.getStatus())
                .build();
    }

    public static UserData generateFullUserDataWithCustomName(UserData userData) {
        return UserData.builder()
                .id(userData.getId())
                .username(userData.getUsername())
                .firstName(getRandomString())
                .lastName(getRandomString())
                .email(getRandomString())
                .password(userData.getPassword())
                .userStatus(userData.getUserStatus())
                .build();
    }

    public static PetData generateMinPetData() {
        return PetData.builder()
                .id(getRandomInt())
                .name(getRandomString())
                .build();
    }

    public static UserData generateFullUserData() {
        return UserData.builder()
                .id(getRandomInt())
                .username(getRandomString())
                .firstName(getRandomString())
                .lastName(getRandomString())
                .email(getRandomString())
                .password(getRandomString())
                .phone(getRandomString())
                .userStatus(getRandomInt())
                .build();
    }

    private static Integer getRandomInt() {
        return new Random().nextInt((65536) - 32768);
    }

    private static String getRandomString() {
        return RandomStringUtils.randomAlphabetic(7);
    }
}
