package com.website.slaven.stajic.ednevnik.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "parent")
public class Parent {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "first_name")
    @NotEmpty(message = "First name cannot be empty")
    @Pattern(regexp = "[a-zA-Z]+", message = "First name must not contain special characters or numbers")
    private String first_name;

    @Column(name = "last_name")
    @NotEmpty(message = "Last name cannot be empty")
    @Pattern(regexp = "[a-zA-Z]+", message = "Last name must not contain special characters or numbers")
    private String last_name;

    @Column(name = "user_user_id")
    private int user_user_id;

    @Column(name = "students_id")
    private int students_id;

}
