package com.project.ordersystem.products;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/products")
public class ProductsController {

    private final ProductsDAO productsDAO;

    public ProductsController(ProductsDAO productsDAO) {
        this.productsDAO = productsDAO;
    }

    @PostMapping("/add")
    public void addEmployee(@RequestBody ProductsModel productsModel){
        productsDAO.addProducts(productsModel);
        //return new ResponseEntity<>("added successfullt", HttpStatus.OK);
    }

    @GetMapping("/all")
    public List<ProductsModel> findAll(){
        return productsDAO.findAll();
    }
}
