package com.example.restfirst.controllerRest;

import com.example.restfirst.dto.ClientForUpdateDto;
import com.example.restfirst.model.Client;
import com.example.restfirst.model.JSONViews.Views;
import com.example.restfirst.service.ClientsService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import javax.validation.Valid;
import java.util.List;


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
    public ResponseEntity<?> saveClient(@RequestBody @Valid Client client, BindingResult bindingResult) {
        System.out.println(client);
        if (bindingResult.hasErrors()) {
            List<FieldError> errors =bindingResult.getFieldErrors();
            return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
        }
        if (client == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        clientsService.saveClient(client);
        return new ResponseEntity<>(client, HttpStatus.CREATED);
    }

    @PutMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateClient(@PathVariable("id") Long id, @RequestBody ClientForUpdateDto clientForUpdateDto, UriComponentsBuilder uriComponentsBuilder) {
        HttpHeaders httpHeaders = new HttpHeaders();
        if (id == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        clientsService.updateClient(id,clientForUpdateDto);
        return new ResponseEntity<>("updated", HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Client> deleteClient(@PathVariable("id") Long id) {
        Client client = clientsService.getById(id).orElse(null);
        if (client==null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        clientsService.deleteClient(client);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView({Views.IdName.class})
    public ResponseEntity<List<Client>> getAllClients() {
        System.out.println("dkifkgigki");
        List<Client> list = clientsService.getAllClients();
        if (list.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }



}

