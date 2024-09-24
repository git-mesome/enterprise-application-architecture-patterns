package io.wisoft.patterns_of_enterprise_application_architecture.datasource.tabledatagateway;

import io.wisoft.patterns_of_enterprise_application_architecture.db.DB;

import java.sql.*;

public class PersonGateway {

  private Connection connection;

  public PersonGateway() throws SQLException {
    this.connection = DB.connect();
  }

  public ResultSet findAll() throws SQLException {
    String sql = "SELECT * FROM person";
    Statement stmt = connection.createStatement();
    return stmt.executeQuery(sql);
  }

  public ResultSet findWithLastName(String lastName) throws SQLException {
    String sql = "SELECT * FROM person WHERE lastname = ?";
    PreparedStatement pstmt = connection.prepareStatement(sql);
    pstmt.setString(1, lastName);
    return pstmt.executeQuery();
  }

  public ResultSet findWhere(String whereClause) throws SQLException {
    String sql = String.format("SELECT * FROM person WHERE %s", whereClause);
    Statement stmt = connection.createStatement();
    return stmt.executeQuery(sql);
  }

  public Object[] findRow(long key) throws SQLException {
    String sql = "SELECT * FROM person WHERE id = ?";
    PreparedStatement pstmt = connection.prepareStatement(sql);
    pstmt.setLong(1, key);

    ResultSet resultSet = pstmt.executeQuery();
    Object[] result = null;

    if (resultSet.next()) {
      int columnCount = resultSet.getMetaData().getColumnCount();
      result = new Object[columnCount];
      for (int i = 1; i <= columnCount; i++) {
        result[i - 1] = resultSet.getObject(i);
      }
    }

    resultSet.close();
    pstmt.close();

    return result;
  }

}