package io.wisoft.patterns_of_enterprise_application_architecture.domainlogic.domainmodel;

import io.wisoft.patterns_of_enterprise_application_architecture.base.money.Money;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Tester {
  private static final Product word = Product.newWordProcessor("Thinking Word");
  private static final Product spread = Product.newSpreadsheet("Thinking spread");
  private static final Product db = Product.newDatabase("Thinking DB");

  public static void main(String[] args) {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

    Money cost1 = Money.dollars(BigDecimal.valueOf(123));
    Money cost2 = Money.dollars(BigDecimal.valueOf(456));
    Money cost3 = Money.dollars(BigDecimal.valueOf(789));

    LocalDate date1 = LocalDate.parse("20200211", formatter);
    LocalDate date2 = LocalDate.parse("20200212", formatter);
    LocalDate date3 = LocalDate.parse("20200213", formatter);

    // Contract 객체 생성
    Contract c1 = new Contract(word, cost1, date1);
    Contract c2 = new Contract(spread, cost2, date2);
    Contract c3 = new Contract(db, cost3, date3);

    c1.calculateRecognitions();
    c2.calculateRecognitions();
    c3.calculateRecognitions();

    c1.recognizedRevenue(date1);
    c2.recognizedRevenue(date2);
    c3.recognizedRevenue(date3);

  }

}
