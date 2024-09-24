package io.wisoft.patterns_of_enterprise_application_architecture.domainlogic.tablemodule;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;

public class RevenueRecognition extends TableModule {

  public RevenueRecognition(Connection connection) throws SQLException {
    super(connection, "RevenueRecognitions");
  }

  // 새로운 수익인식 레코드 삽입 메서드
  public long insert(long contractID, BigDecimal amount, LocalDate date) throws SQLException {
    String query = "INSERT INTO RevenueRecognitions(contractID, amount, date) VALUES (?, ?, ?)";
    int affectedRows = executeUpdate(query, contractID, amount, date);
    if (affectedRows > 0) {
      // 생성된 아이디 검색
      try (Statement stmt = connection.createStatement();
           ResultSet generatedKeys = stmt.executeQuery("SELECT LAST_INSERT_ID()")) {
        if (generatedKeys.next()) {
          return generatedKeys.getLong(1);
        } else {
          throw new SQLException("Creating revenue recognition failed, no ID obtained.");
        }
      }
    } else {
      throw new SQLException("Creating revenue recognition failed, no rows affected.");
    }
  }

  // 수익인식 계산
  public BigDecimal recognizedRevenue(long contractID, LocalDate asOf) throws SQLException {
    String query = "SELECT SUM(amount) AS total FROM RevenueRecognitions WHERE contractID = ? AND date <= ?";
    try (ResultSet rs = executeQuery(query, contractID, asOf)) {
      if (rs.next()) {
        return rs.getBigDecimal("total") != null ? rs.getBigDecimal("total") : BigDecimal.ZERO;
      } else {
        return BigDecimal.ZERO;
      }
    }
  }
}