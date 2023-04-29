package ru.wear.store.resource.server.repositories;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import ru.wear.store.resource.server.models.ClientOrder;

public interface ClientOrderRepository extends ReactiveCrudRepository<ClientOrder,Long> {
}
