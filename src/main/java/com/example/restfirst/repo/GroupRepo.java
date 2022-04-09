package com.example.restfirst.repo;
import com.example.restfirst.model.GroupUni;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GroupRepo extends JpaRepository<GroupUni,Long> {
    Optional<GroupUni> findByGroupNumber(int number);
}
