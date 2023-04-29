package ru.wear.store.resource.server.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.wear.store.resource.server.models.Client;
import ru.wear.store.resource.server.repositories.ClientRepository;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:8080")
public class AuthController {

    private final ClientRepository clientRepository;

    @GetMapping()
    @ResponseStatus(HttpStatus.FOUND)
    public Flux<Client> getClients(){
        return clientRepository.findAll();
    }

    @GetMapping("/id/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public Mono<Client> getClientById(@PathVariable("id") long id){
        return clientRepository.findById(id);
    }

    @GetMapping("/username/{username}")
    @ResponseStatus(HttpStatus.FOUND)
    public Mono<Client> getClientByUsername(@PathVariable("username") String username){
        return clientRepository.findByUsername(username);
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Client> saveClient(@RequestBody Mono<Client> clientMono){
        //return clientRepository.saveAll(clientMono).next();
        return clientMono.flatMap(clientRepository :: save);
    }
}
