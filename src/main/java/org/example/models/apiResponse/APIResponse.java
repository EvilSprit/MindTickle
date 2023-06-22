package org.example.models.apiResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class APIResponse {
    private Integer code;
    private String type;
    private String message;
}
