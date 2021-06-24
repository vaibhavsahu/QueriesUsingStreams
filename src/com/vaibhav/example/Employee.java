package com.vaibhav.example;

import lombok.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
public class Employee {
    int id;

    String name;

    int age;

    String gender;

    String department;

    int yearOfJoining;

    double salary;
}
