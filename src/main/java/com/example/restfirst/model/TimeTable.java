package com.example.restfirst.model;

import com.example.restfirst.model.modelForTimeTable.Day;
import com.example.restfirst.model.modelForTimeTable.Period;
import com.example.restfirst.model.modelForTimeTable.Subject;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "time_table")
public class TimeTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "subject_uni")
    private Subject subject;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "day_id")
    private Day day;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "time_id")
    private Period period;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "group_id")
    private GroupUni groupUni;
}
