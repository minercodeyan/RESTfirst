package com.example.restfirst.repo;

import com.example.restfirst.model.communicationEntities.UniversityFile;
import org.springframework.data.repository.CrudRepository;

public interface FileRepo extends CrudRepository<UniversityFile,Long> {
}
