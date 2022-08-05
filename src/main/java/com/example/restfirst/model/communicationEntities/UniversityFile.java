package com.example.restfirst.model.communicationEntities;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "files")
public class UniversityFile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name="filename")
    private String filename;

    @Column(name = "file_extension")
    private String fileExtension;
}
