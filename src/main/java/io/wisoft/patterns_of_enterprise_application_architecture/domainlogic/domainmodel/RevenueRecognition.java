package io.wisoft.patterns_of_enterprise_application_architecture.domainlogic.domainmodel;

import io.wisoft.patterns_of_enterprise_application_architecture.base.money.Money;

import java.time.LocalDate;

public class RevenueRecognition {

  private final Money amount;
  private final LocalDate date;

  public RevenueRecognition(Money amount, LocalDate date) {
    this.amount = amount;
    this.date = date;
  }

  public Money getAmount() {
    return amount;
  }

  boolean isRecognizableBy(LocalDate asOf) {
    return asOf.equals(date) || date.isAfter(asOf);
  }

}
