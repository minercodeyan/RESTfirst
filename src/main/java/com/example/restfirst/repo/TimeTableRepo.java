package com.example.restfirst.repo;

import com.example.restfirst.model.TimeTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TimeTableRepo extends JpaRepository<TimeTable, Long> {

    List<TimeTable> findAllByGroupUniGroupNumber(Integer number);
}
