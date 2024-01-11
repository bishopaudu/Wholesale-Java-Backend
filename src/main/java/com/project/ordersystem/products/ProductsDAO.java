package com.project.ordersystem.products;

import org.springframework.stereotype.Repository;

import java.util.List;


public interface ProductsDAO {
    List<ProductsModel> findAll();
    int addProducts(ProductsModel productsModel);
    int deleteProducts(int id);
    int updateProducts(int id, ProductsModel productsModel);
}
