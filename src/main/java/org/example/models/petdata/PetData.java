package org.example.models.petdata;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PetData {
    private Integer id;
    private CategoryData category;
    private String name;
    private List<String> photoUrls;
    private List<TagData> tags;
    private Status status;
}
