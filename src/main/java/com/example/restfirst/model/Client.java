package com.example.restfirst.model;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;


@Entity
@Table(name = "clients")
@Getter
@Setter
@ToString
public class Client extends BaseEntity {
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
    @Column(name = "date_of_birth")
    @DateTimeFormat(style = "SS")
    private Date dateOfBirth;
}
