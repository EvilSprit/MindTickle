package org.example.models.petdata;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class TagData {
    private Integer id;
    private String name;
}
