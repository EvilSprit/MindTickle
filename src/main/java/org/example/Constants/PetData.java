package org.example.Constants;

import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Builder
@Getter
public class PetData {
    private String id;
    private String categoryID;
    private String categoryName;
    private String petName;
    private List<String> photoUrls;
    private Map<String, String> tags;
    private String status;
}
