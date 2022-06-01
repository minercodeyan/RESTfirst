package com.example.restfirst.repo;

import com.example.restfirst.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientsRepo extends JpaRepository<Client,Long> {


}
