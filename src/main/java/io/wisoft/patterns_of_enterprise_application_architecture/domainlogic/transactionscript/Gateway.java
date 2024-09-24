package io.wisoft.patterns_of_enterprise_application_architecture.domainlogic.transactionscript;

import io.wisoft.patterns_of_enterprise_application_architecture.base.money.Money;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

//데이터베이스 접근 계층
public class Gateway {

  //수익 인식 날짜 검색
  public ResultSet findRecognitionsFor(long contractId, LocalDate asOf) throws SQLException {
    PreparedStatement stmt = db.prepareStatement(findRecognitionsStatement);
    stmt.setLong(1, contractId);
    stmt.setObject(2, asOf);
    return stmt.executeQuery();
  }

  //계약 검색
  public ResultSet findContract(long contractID) throws SQLException {
    PreparedStatement stmt = db.prepareStatement(findContractStatement);
    stmt.setLong(1, contractID);
    return stmt.executeQuery();
  }

  //삽입을 위한 래퍼
  public void insertRecognition(long contractID, Money amount, LocalDate asOf) throws SQLException {
    PreparedStatement stmt = db.prepareStatement(insertRecognitionStatement);
    stmt.setLong(1, contractID);
    stmt.setBigDecimal(2, amount.amount());
    stmt.setObject(3, asOf);
    stmt.executeUpdate();
  }

  private static final String findRecognitionsStatement =
      "SELECT amount " +
          "FROM revenueRecognitions " +
          "WHERE contract=? AND recognizedOn <= ?";

  private static final String findContractStatement =
      "SELECT * " +
          " FROM contracts c, products p " +
          " WHERE ID = ? AND c.product = p.ID";

  private static final String insertRecognitionStatement =
      "INSERT INTO revenueRecognitions VALUES (?, ?, ?)";

  private Connection db;

}
