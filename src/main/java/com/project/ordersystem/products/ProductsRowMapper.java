package com.project.ordersystem.products;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductsRowMapper implements RowMapper<ProductsModel> {
    @Override
    public ProductsModel mapRow(ResultSet rs, int rowNum) throws SQLException {
       ProductsModel productsModel = new ProductsModel();
                productsModel.setID(rs.getInt("id"));
               productsModel.setSKU(rs.getString("sku"));
               productsModel.setDescription(rs.getString("description"));
                productsModel.setCategory(rs.getString("category"));
                productsModel.setPrice(rs.getBigDecimal("price"));
                return productsModel;

    }
}
