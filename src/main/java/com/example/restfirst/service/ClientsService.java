package com.example.restfirst.service;

import com.example.restfirst.dto.ClientForUpdateDto;
import com.example.restfirst.model.Client;

import java.util.List;
import java.util.Optional;

public interface ClientsService {

    Optional<Client> getById(Long id);

    void saveClient(Client client);

    void deleteClient(Client client);

    void updateClient(Long id,ClientForUpdateDto clientForUpdateDto);

    List<Client> getAllClients();


}
