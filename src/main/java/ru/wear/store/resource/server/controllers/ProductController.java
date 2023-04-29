package ru.wear.store.resource.server.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.wear.store.resource.server.models.Product;
import ru.wear.store.resource.server.repositories.ProductRepository;

@RestController
@RequestMapping("/catalog")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:8080")
public class ProductController {

    private final ProductRepository productRepository;

    @GetMapping("/get/all")
    @ResponseStatus(HttpStatus.FOUND)
    public Flux<Product> getAll(){
        return productRepository.findAll();
    }

    @GetMapping("/get/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public Mono<Product> findById(@PathVariable(name = "id") long id){
        return productRepository.findById(id);
    }

    @PostMapping(path = "/add", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Product> saveProduct(@RequestBody Mono<Product> productMono){
        return productMono.flatMap(productRepository :: save);
    }
}
