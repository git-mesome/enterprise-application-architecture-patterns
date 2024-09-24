package io.wisoft.patterns_of_enterprise_application_architecture.domainlogic.tablemodule;

import java.sql.*;

public class TableModule {

  protected Connection connection;
  protected ResultSet resultSet;
  private final String tableName;

  protected TableModule(Connection connection, String tableName) throws SQLException {
    this.connection = connection;
    this.tableName = tableName;
    Statement statement = connection.createStatement();
    String query = "SELECT * FROM " + tableName;
    resultSet = statement.executeQuery(query);
  }

  protected ResultSet executeQuery(String query, Object... params) throws SQLException {
    PreparedStatement stmt = connection.prepareStatement(query);
    for (int i = 0; i < params.length; i++) {
      stmt.setObject(i + 1, params[i]);
    }
    return stmt.executeQuery();
  }

  // Method to execute an update query
  protected int executeUpdate(String query, Object... params) throws SQLException {
    try (PreparedStatement stmt = connection.prepareStatement(query)) {
      for (int i = 0; i < params.length; i++) {
        stmt.setObject(i + 1, params[i]);
      }
      return stmt.executeUpdate();
    }
  }

}