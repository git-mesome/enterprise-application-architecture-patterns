package io.wisoft.patterns_of_enterprise_application_architecture.domainlogic.domainmodel;

import io.wisoft.patterns_of_enterprise_application_architecture.base.money.Money;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Contract {

  private Product product;
  private Money revenue;
  private LocalDate whenSigned;
  private Long id;

  private final List<RevenueRecognition> revenueRecognitions = new ArrayList<>();

  public Contract(Product product, Money revenue, LocalDate whenSigned) {
    this.product = product;
    this.revenue = revenue;
    this.whenSigned = whenSigned;
  }

  // 특정 날짜까지 인식된 수익 계산 로직
  public Money recognizedRevenue(LocalDate asOf) {
    Money result = Money.dollars(BigDecimal.ZERO);

    result = revenueRecognitions.stream()
        .filter(re -> re.isRecognizableBy(asOf)) // 필터 조건에 맞는 수익 인식만 선택
        .map(RevenueRecognition::getAmount)      // 금액 추출
        .reduce(result, Money::add);             // 금액을 합산

    return result;
  }

  public void calculateRecognitions() {
    product.calculateRevenueRecognitions(this);
  }

  public void addRevenueRecognition(RevenueRecognition revenueRecognition) {
    revenueRecognitions.add(revenueRecognition);
  }

  public Money getRevenue() {
    return  revenue;
  }

  public LocalDate getWhenSigned() {
    return whenSigned;
  }


}
