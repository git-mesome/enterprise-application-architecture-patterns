package io.wisoft.patterns_of_enterprise_application_architecture.base.specialcase;

import io.wisoft.patterns_of_enterprise_application_architecture.domainlogic.domainmodel.Contract;

import java.math.BigDecimal;

public class Employee {

  private String name;
  private Contract contract;

  public String getName() {
    return name;
  }

  public Contract getContract() {
    return contract;
  }

  public void setName(String name) {
    this.name = name;
  }

  //현재까지 총합
  public BigDecimal getGrossToDate() {
    return calculateGrossFromPeriod(0);
  }

  private BigDecimal calculateGrossFromPeriod(int period) {
    return BigDecimal.ZERO;  // 기본 반환
  }

}
