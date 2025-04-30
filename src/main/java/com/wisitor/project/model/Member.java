package com.wisitor.project.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;


import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String contact;
    @Value("Earth")
    private String address;
    @OneToOne
    @JoinColumn(name = "username")
    private User user;

    @OneToMany(mappedBy = "member")
    @JsonManagedReference
    private List<Visitor> visitors;
}
