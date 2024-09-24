package io.wisoft.patterns_of_enterprise_application_architecture.domainlogic.tablemodule;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

public class Contract extends TableModule {

  public Contract(Connection connection) throws SQLException {
    super(connection, "Contracts");
  }

  public ResultSet getRowById(long key) throws SQLException {
    String query = String.format("SELECT * FROM Contracts WHERE ID = %d", key);
    Statement statement = connection.createStatement();
    return statement.executeQuery(query);
  }

  public void calculateRecognitions(long contractID) throws SQLException {
    ResultSet contractRow = getRowById(contractID);
    if (!contractRow.next()) {
      throw new SQLException("Contract not found");
    }

    BigDecimal amount = contractRow.getBigDecimal("amount");
    RevenueRecognition rr = new RevenueRecognition(connection);
    Product prod = new Product(connection);
    long prodID = getProductId(contractID);

    ProductType productType = prod.getProductType(prodID);

    switch (productType) {
      case WP:
        rr.insert(contractID, amount, getWhenSigned(contractID));
        break;
      case SS:
        BigDecimal[] allocationSS = allocate(amount, 3);
        rr.insert(contractID, allocationSS[0], getWhenSigned(contractID));
        rr.insert(contractID, allocationSS[1], getWhenSigned(contractID).plusDays(60));
        rr.insert(contractID, allocationSS[2], getWhenSigned(contractID).plusDays(90));
        break;
      case DB:
        BigDecimal[] allocationDB = allocate(amount, 3);
        rr.insert(contractID, allocationDB[0], getWhenSigned(contractID));
        rr.insert(contractID, allocationDB[1], getWhenSigned(contractID).plusDays(30));
        rr.insert(contractID, allocationDB[2], getWhenSigned(contractID).plusDays(60));
        break;
      default:
        throw new IllegalArgumentException("Invalid product id");
    }
  }

  private BigDecimal[] allocate(BigDecimal amount, int by) {
    BigDecimal lowResult = amount.divide(BigDecimal.valueOf(by), 2, BigDecimal.ROUND_HALF_UP);
    BigDecimal highResult = lowResult.add(BigDecimal.valueOf(0.01));
    BigDecimal[] results = new BigDecimal[by];
    int remainder = amount.remainder(BigDecimal.valueOf(by)).intValue();
    for (int i = 0; i < remainder; i++) results[i] = highResult;
    for (int i = remainder; i < by; i++) results[i] = lowResult;
    return results;
  }

  // Dummy methods for placeholders
  private long getProductId(long contractID) {
    // Implement logic to get product ID from database
    return 0;
  }

  private LocalDate getWhenSigned(long contractID) {
    // Implement logic to get date when signed from database
    return LocalDate.now();
  }
}
