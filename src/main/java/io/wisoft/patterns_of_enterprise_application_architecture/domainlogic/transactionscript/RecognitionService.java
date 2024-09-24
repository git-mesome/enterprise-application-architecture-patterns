package io.wisoft.patterns_of_enterprise_application_architecture.domainlogic.transactionscript;

import io.wisoft.patterns_of_enterprise_application_architecture.base.money.Money;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

//비즈니스 로직 구현
public class RecognitionService {
  private final Gateway db;

  public RecognitionService(Gateway db) {
    this.db = db;
  }

  public Money recognizedRevenue(long contractNumber, LocalDate asOf) {
    Money result = Money.dollars(BigDecimal.ZERO);

    try (ResultSet rs = db.findRecognitionsFor(contractNumber, asOf)) {
      while (rs.next()) {
        BigDecimal amount = rs.getBigDecimal("amount");
        result = result.add(Money.dollars(amount));
      }
      return result;
    } catch (SQLException e) {
      throw new IllegalStateException(e);
    }
  }

  public void calculateRevenueRecognitions(long contractNumber) {
    try (ResultSet contracts = db.findContract(contractNumber)) {
      contracts.next();

      //수익 총합
      Money totalRevenue = Money.dollars(contracts.getBigDecimal("amount"));
      //계약 날짜
      LocalDate recognitionDate = contracts.getObject("dateSigned", LocalDate.class);
      //상품 종류
      String type = contracts.getString("type");

      //상품별 수익 인식 계산
      switch (type) {
        case "S" -> {
          Money[] allocation = totalRevenue.allocate(3);
          db.insertRecognition(contractNumber, allocation[0], recognitionDate);
          db.insertRecognition(contractNumber, allocation[1], recognitionDate.plusDays(60));
          db.insertRecognition(contractNumber, allocation[2], recognitionDate.plusDays(90));
        }
        case "W" -> db.insertRecognition(contractNumber, totalRevenue, recognitionDate);
        case "D" -> {
          Money[] allocation = totalRevenue.allocate(3);
          db.insertRecognition(contractNumber, allocation[0], recognitionDate);
          db.insertRecognition(contractNumber, allocation[1], recognitionDate.plusDays(30));
          db.insertRecognition(contractNumber, allocation[2], recognitionDate.plusDays(60));
        }
      }

    } catch (SQLException e) {
      throw new IllegalStateException(e);
    }
  }

}
