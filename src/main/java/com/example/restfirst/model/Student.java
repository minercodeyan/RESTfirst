package com.example.restfirst.model;

import com.example.restfirst.model.JSONViews.Views;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;


@Entity
@Table(name = "students")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @JsonView({Views.IdName.class})
    private Long id;

    @Column(name = "name")
    @JsonView({Views.IdName.class})
    private String name;

    @Column(name = "surname")
    @Size(min = 1,max = 20)
    @JsonView({Views.IdName.class})
    private String surname;

    @Column(name = "patronymic")
    @NotEmpty
    private String patronymic;

    @Column(name="sex")
    private String sex;

    @Column(name = "date_of_birth")
    @DateTimeFormat(style = "SS")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonProperty(value = "dateOfBirth")
    private Date dateOfBirth;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "group_id")
    @JsonIgnore
    private GroupUni groupUni;
}
