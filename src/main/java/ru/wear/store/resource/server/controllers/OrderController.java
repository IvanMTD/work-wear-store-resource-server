package ru.wear.store.resource.server.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import ru.wear.store.resource.server.models.Client;
import ru.wear.store.resource.server.models.ClientOrder;
import ru.wear.store.resource.server.models.Product;
import ru.wear.store.resource.server.repositories.ClientOrderRepository;
import ru.wear.store.resource.server.repositories.ClientRepository;
import ru.wear.store.resource.server.repositories.ProductRepository;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:8080")
public class OrderController {

    private final ClientOrderRepository orderRepository;
    private final ClientRepository clientRepository;
    private final ProductRepository productRepository;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<ClientOrder> saveProduct(@RequestBody Mono<ClientOrder> orderMono){
        return orderMono
                .map(order -> {
                  ClientOrder clientOrder = new ClientOrder();
                  clientOrder.addClient(order.getClient() == null? new Client() : order.getClient());
                  for(Product product : order.getProducts()){
                      clientOrder.addProduct(product);
                  }
                  return clientOrder;
                })
                .flatMap(orderRepository::save);
    }
}
