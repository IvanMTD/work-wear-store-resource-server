package ru.wear.store.resource.server.models;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Table;

import java.util.ArrayList;
import java.util.List;

@Data
@RequiredArgsConstructor
@Table(name = "ClientOrders")
public class ClientOrder {
    @Id
    private long id;
    private long clientId;
    private List<Long> productsId = new ArrayList<>();

    @Transient
    private Client client;
    @Transient
    private List<Product> products = new ArrayList<>();

    public void addProduct(Product product){
        System.out.println(product.getId());

        productsId.add(product.getId());
        System.out.println(productsId);
    }

    public void addClient(Client client){
        System.out.println(client.getId());
        clientId = client.getId();
    }
}
