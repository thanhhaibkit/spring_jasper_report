package com.example.jasperreport.dto;

import lombok.Data;

@Data
public class Employee {
    private int id;
    private String name;
    private String organization;
    private String designation;
    private int salary;

    public Employee(int id, String name, String organization, String designation, int salary) {
        super();
        this.id = id;
        this.name = name;
        this.organization = organization;
        this.designation = designation;
        this.salary = salary;
    }
}
