package com.example.wound.rest.requests;

import com.example.wound.rest.enums.WoundLocation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddWoundRequest {

    private WoundLocation location;

    private String name;

    private Long patientId;
}
