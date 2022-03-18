package com.example.restfirst.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;


@Entity
@Table(name = "clients")
@Getter
@Setter
@ToString
public class Client extends BaseEntity {
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "patronymic")
    private String patronymic;
    @Column(name = "date_of_birth")
    private Date dateOfBirth;
    @Column(name = "sex")
    private boolean sex;
    @Column(name = "passport_serries")
    private String passportSerries;
    @Column(name = "passport_number")
    private int passportNumber;
    @Column(name = "passport_date_of_issue")
    private Date passportDateOfIssue;
    @Column(name = "place_of_birth")
    private String placeOfBirth;
    @Column(name = "residential_address")
    private String residentialAddress;
    @Column(name = "phonenumber")
    private int phoneNumber;
    @Column(name = "family_state_id")
    private Long familyStateId;
    @Column(name = "retiree_id")
    private Long retireeId;
}
