package com.centime.microservice1.model;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Person {

    @NotEmpty(message = "Name must not be empty.")
    @Size(min = 5, message = "Name should be minimum of 5 characters.")
    private String name;
    @NotEmpty(message = "Surname must not be empty.")
    private String surname;

}
