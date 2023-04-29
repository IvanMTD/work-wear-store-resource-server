package ru.wear.store.resource.server.repositories;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import ru.wear.store.resource.server.models.Product;

public interface ProductRepository extends ReactiveCrudRepository<Product,Long> {

}
