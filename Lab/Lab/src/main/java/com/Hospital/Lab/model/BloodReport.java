package com.Hospital.Lab.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BloodReport {
    private int id;
    private String area;
    private String hemoglobin;
    private String rbc;
    private String wbc;
}
