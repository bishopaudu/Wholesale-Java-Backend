package com.project.ordersystem.products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class ProductsDAOImpl implements ProductsDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ProductsDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public List<ProductsModel> findAll() {
        String sql = """
           SELECT id,sku,description,category,price
           FROM products
           LIMIT 100;
           """;
        return jdbcTemplate.query(sql,new ProductsRowMapper());
    }

    @Override
    public int addProducts(ProductsModel productsModel) {
        String sql = """
        INSERT INTO products (id, sku, description, category, price)
        VALUES (?, ?, ?, ?, ?)
        """;
        return jdbcTemplate.update(sql,
                productsModel.getID(),
                productsModel.getSKU(),
                productsModel.getDescription(),
                productsModel.getCategory(),
                productsModel.getPrice());
    }


    @Override
    public int deleteProducts(int id) {
        String sql = """
           DELETE FROM products
           WHERE id = ?;
           """;
        return jdbcTemplate.update(sql,id);
    }

    @Override
    public int updateProducts(int id, ProductsModel productsModel) {
        String sql = """
           UPDATE products
           SET  sku = ?, description = ?, category = ? price = ?
           WHERE id = ?;
           """;
        return jdbcTemplate.update(sql,
                productsModel.getSKU(),
                productsModel.getDescription(),
                productsModel.getCategory(),
                productsModel.getPrice(),id);
    }

}
