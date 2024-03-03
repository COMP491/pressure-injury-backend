package com.example.wound.dto;

import com.example.wound.rest.enums.WoundLocation;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatientWoundsDTO {

    private Long id;

    private WoundLocation location;

    private String name;

}
