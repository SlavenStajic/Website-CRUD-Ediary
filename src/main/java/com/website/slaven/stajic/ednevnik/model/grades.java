package com.website.slaven.stajic.ednevnik.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "grades")
public class grades {

    @Id
    @Column(name = "id")
    private int id;

    @NotEmpty(message = "field can not be empty")
    @Pattern(regexp = "[1-5]", message = "Invalid grade")
    @Column(name = "English")
    private String English;


    @NotEmpty(message = "field can not be empty")
    @Pattern(regexp = "[1-5]", message = "Invalid grade")
    @Column(name = "Math")
    private String Math;


    @NotEmpty(message = "field can not be empty")
    @Pattern(regexp = "[1-5]", message = "Invalid grade")
    @Column(name = "Physics")
    private String Physics;


    @NotEmpty(message = "field can not be empty")
    @Pattern(regexp = "[1-5]", message = "Invalid grade")
    @Column(name = "Art")
    private String Art;


    @NotEmpty(message = "field can not be empty")
    @Pattern(regexp = "[1-5]", message = "Invalid grade")
    @Column(name = "Music")
    private String Music;

    @Column(name = "students_id")
   private int students_id;



}
