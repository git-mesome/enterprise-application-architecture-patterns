package io.wisoft.patterns_of_enterprise_application_architecture.db;

import java.sql.*;

public class DB {
  public static Connection connect() throws SQLException {

    try {
      String url = "jdbc:postgresql://localhost:54321/jdbc";
      String user = "minseo";
      String password = "minseo";

      return DriverManager.getConnection(url, user, password);

    } catch (SQLException e) {
      System.err.println(e.getMessage());
      return null;
    }
  }
}
