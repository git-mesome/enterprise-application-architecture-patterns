package io.wisoft.patterns_of_enterprise_application_architecture.datasource.rowdatagateway.indomainmodel;

import io.wisoft.patterns_of_enterprise_application_architecture.base.money.Money;
import io.wisoft.patterns_of_enterprise_application_architecture.datasource.rowdatagateway.PersonGateway;

import java.math.BigDecimal;

public class Person {
  private PersonGateway data;

  public Person(PersonGateway data) {
    this.data = data;
  }

  public int getNumberOfDependents() {
    return data.getNumberOfDependents();
  }

  public Money getExemption() {
    Money baseExemption = Money.dollars(BigDecimal.valueOf(1500));
    Money dependentExemption = Money.dollars(BigDecimal.valueOf(750));
    return baseExemption.add(dependentExemption.multiply(this.getNumberOfDependents()));
  }
}
