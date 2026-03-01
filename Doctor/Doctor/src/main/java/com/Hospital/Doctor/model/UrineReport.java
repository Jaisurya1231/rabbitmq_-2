package com.Hospital.Doctor.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UrineReport {
    private int id;
    private String area;
    private String color;
    private String appearance;
    private boolean nitrite;
    private boolean glucose;
    private boolean bilirubin;
}
