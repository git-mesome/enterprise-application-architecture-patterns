package io.wisoft.patterns_of_enterprise_application_architecture.domainlogic.tablemodule;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

enum ProductType { WP, SS, DB }

public class Product extends TableModule {

  public Product(Connection connection) throws SQLException {
    super(connection, "Products");
  }

  public ProductType getProductType(long id) throws SQLException {
    try (ResultSet rs = getRowById(id)) {  // Using inherited method from TableModule
      if (rs.next()) {
        String typeCode = rs.getString("type");
        return ProductType.valueOf(typeCode);
      } else {
        throw new SQLException("Product not found with ID: " + id);
      }
    }
  }
}