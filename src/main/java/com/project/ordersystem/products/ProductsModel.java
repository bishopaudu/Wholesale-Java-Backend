package com.project.ordersystem.products;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductsModel {
    private int ID;
    private String SKU;
    private String description;
    private String category;
    private BigDecimal price;
}
