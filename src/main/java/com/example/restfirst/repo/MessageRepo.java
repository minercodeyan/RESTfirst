package com.example.restfirst.repo;

import com.example.restfirst.model.communicationentities.Massage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepo extends JpaRepository<Massage,Long> {
}
