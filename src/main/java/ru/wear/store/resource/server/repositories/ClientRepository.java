package ru.wear.store.resource.server.repositories;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;
import ru.wear.store.resource.server.models.Client;

public interface ClientRepository extends ReactiveCrudRepository<Client,Long> {
    Mono<Client> findByUsername(String username);
}
