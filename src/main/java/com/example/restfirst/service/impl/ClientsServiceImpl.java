package com.example.restfirst.service.impl;


import com.example.restfirst.dto.ClientForUpdateDto;
import com.example.restfirst.model.Client;
import com.example.restfirst.repo.ClientsRepo;
import com.example.restfirst.service.ClientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientsServiceImpl implements ClientsService {
    private final ClientsRepo clientsRepo;

    @Autowired
    public ClientsServiceImpl(ClientsRepo clientsRepo) {
        this.clientsRepo = clientsRepo;
    }

    @Override
    public Optional<Client> getById(Long id) {
        return clientsRepo.findById(id);
    }

    @Override
    public void saveClient(Client client) {
        clientsRepo.save(client);
    }

    @Override
    public void deleteClient(Client client) {
        clientsRepo.delete(client);
    }

    @Override
    public void updateClient(Long id,ClientForUpdateDto clientForUpdateDto) {
        Client client = clientsRepo.findById(id).orElseThrow();
        client.setName(clientForUpdateDto.getName());
        client.setSurname(clientForUpdateDto.getSurname());
        clientsRepo.save(client);
    }
    @Override
    public List<Client> getAllClients() {
       return clientsRepo.findAll();
    }
}
