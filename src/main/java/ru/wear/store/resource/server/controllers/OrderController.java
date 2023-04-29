package ru.wear.store.resource.server.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import ru.wear.store.resource.server.models.ClientOrder;
import ru.wear.store.resource.server.models.Product;
import ru.wear.store.resource.server.repositories.ClientOrderRepository;
import ru.wear.store.resource.server.repositories.ClientRepository;
import ru.wear.store.resource.server.repositories.ProductRepository;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:8080")
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final ClientOrderRepository orderRepository;
    private final ClientRepository clientRepository;
    private final ProductRepository productRepository;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<ClientOrder> saveProduct(@RequestBody Mono<ClientOrder> productMono){
        System.out.println("Срабатывает контролер");
        return productMono
                .flatMap(order -> {
                  order.addClient(order.getClient());
                  for(Product product : order.getProducts()){
                      order.addProduct(product);
                  }
                  return Mono.just(order);
                })
                .flatMap(orderRepository::save);
    }
}
