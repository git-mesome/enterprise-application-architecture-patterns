package io.wisoft.patterns_of_enterprise_application_architecture.base.specialcase;

import io.wisoft.patterns_of_enterprise_application_architecture.domainlogic.domainmodel.Contract;

import java.math.BigDecimal;

public class NullEmployee extends Employee {

  //null 값 처리
  public String getName() {
    return "Null Employee";
  }

  @Override
  public BigDecimal getGrossToDate() {
    return BigDecimal.ZERO;
  }

  @Override
  public Contract getContract() {
    throw new IllegalStateException("Contract not available");
  }
}
