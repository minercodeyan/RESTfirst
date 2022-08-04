package com.example.restfirst.model.communicationentities;

import com.example.restfirst.model.GroupUni;
import com.example.restfirst.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;


@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "massages")
public class Massage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @JsonProperty(value = "id")
    private Long id;

    @NotEmpty
    @Column(name = "description")
    @JsonProperty(value = "description")
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @JsonProperty(value = "creator")
    private User user;

    @DateTimeFormat
    @Column(name="creation_date")
    @JsonProperty(value = "localDate")
    private Date date;



}
