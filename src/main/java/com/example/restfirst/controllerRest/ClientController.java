package com.example.restfirst.controllerRest;


import com.example.restfirst.model.Client;
import com.example.restfirst.service.ClientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/clients")
public class ClientController {

    private final ClientsService clientsService;

    @Autowired
    public ClientController(ClientsService clientsService) {
        this.clientsService = clientsService;
    }

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Client> getClient(@PathVariable("id") Long clientId) {
        if (clientId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Client client = clientsService.getById(clientId).orElse(null);
        if (client == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Client> saveClient(@RequestBody @Valid Client client, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            System.out.println(1);
        if (client == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        clientsService.saveClient(client);
        return new ResponseEntity<>(client, HttpStatus.CREATED);
    }

    @PutMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Client> updateClient(@RequestBody @Valid Client client, UriComponentsBuilder uriComponentsBuilder) {
        HttpHeaders httpHeaders=new HttpHeaders();
        if (client == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        clientsService.saveClient(client);
        return new ResponseEntity<>(client,httpHeaders,HttpStatus.OK);
    }

    @DeleteMapping(value = "",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Client> updateClient(Long id) {
        Optional<Client> client = clientsService.getById(id);
        if (client == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        clientsService.deleteClient(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Client>> getAllCustomers(){
        List<Client> list = clientsService.getAllClients();
        if(list.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(list,HttpStatus.OK);
    }
}
