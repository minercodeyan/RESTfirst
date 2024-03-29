package com.example.restfirst.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "groups")
public class GroupUni {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "group_number")
    private int groupNumber;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "groupUni",cascade = CascadeType.ALL)
    private Set<Student> studentSet;

    public GroupUni(Long id) {
        this.id = id;
    }
}
