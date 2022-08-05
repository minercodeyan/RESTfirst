package com.example.restfirst.model.communicationEntities;

import com.example.restfirst.model.JSONViews.Views;
import com.example.restfirst.model.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;


@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "massages")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @JsonProperty(value = "id")
    @JsonView({Views.MessageUser.class})
    private Long id;

    @NotEmpty
    @Column(name = "description")
    @JsonProperty(value = "description")
    @JsonView({Views.MessageUser.class})
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @JsonView({Views.MessageUser.class})
    private User user;

    @DateTimeFormat
    @Column(name="creation_date")
    @JsonProperty(value = "localDate")
    @JsonView({Views.MessageUser.class})
    private Date date;



}
