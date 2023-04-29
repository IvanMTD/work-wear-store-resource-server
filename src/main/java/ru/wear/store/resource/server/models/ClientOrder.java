package ru.wear.store.resource.server.models;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serial;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
@RequiredArgsConstructor
@Table(name = "ClientOrders")
public class ClientOrder {
    @Id
    @Serial
    private long id;
    @Transient
    private Client client;
    private long clientId;
    @Transient
    private List<Product> products = new ArrayList<>();
    private Set<Long> productsId;

    public void addProduct(Product product){
        productsId.add(product.getId());
    }

    public void addClient(Client client){
        clientId = client.getId();
    }
}
