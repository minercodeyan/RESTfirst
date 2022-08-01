package com.example.restfirst.model.communicationentities;

import com.example.restfirst.model.GroupUni;
import com.example.restfirst.model.User;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.time.LocalTime;


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
    private Long id;

    @NotEmpty
    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @DateTimeFormat
    private LocalDate localDate;

    private LocalTime localTime;

}
