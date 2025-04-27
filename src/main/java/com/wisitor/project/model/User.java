package com.wisitor.project.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class User {
//    @Id
//    private int id;
    @Id
    private String username;
    private String password;
    private String role;

    @OneToOne(mappedBy = "user")
    private Member member;
    @OneToOne(mappedBy = "user")
    private Guard guard;
}
