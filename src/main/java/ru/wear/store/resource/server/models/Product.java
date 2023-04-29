package ru.wear.store.resource.server.models;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serial;
import java.math.BigDecimal;

@Data
@RequiredArgsConstructor
@Table(name = "Products")
public class Product {
    @Id
    @Serial
    private long id;
    private String name;
    private String description;
    private byte[] image;
    private String size;
    private BigDecimal coast;
    private int discount;
    private boolean outOfStock;
}
