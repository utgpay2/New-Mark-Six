package com.central.backend.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminRolesResponse {
    private long id;
    private String name;
    private String description;
    List<Long> permissionsId;


}


