package io.wisoft.patterns_of_enterprise_application_architecture.domainlogic.domainmodel;

import io.wisoft.patterns_of_enterprise_application_architecture.base.money.Money;

public class ThreeWayRecognitionStrategy extends RecognitionStrategy {

  private final int firstRecognitionOffset;
  private final int secondRecognitionOffset;

  public ThreeWayRecognitionStrategy(int firstRecognitionOffset, int secondRecognitionOffset) {
    this.firstRecognitionOffset = firstRecognitionOffset;
    this.secondRecognitionOffset = secondRecognitionOffset;
  }

  @Override
  void calculateRevenueRecognitions(Contract contract) {
    Money[] allocation = contract.getRevenue().allocate(3);
    contract.addRevenueRecognition(new RevenueRecognition(allocation[0], contract.getWhenSigned()));
    contract.addRevenueRecognition(new RevenueRecognition(allocation[1], contract.getWhenSigned().plusDays(firstRecognitionOffset)));
    contract.addRevenueRecognition(new RevenueRecognition(allocation[2], contract.getWhenSigned().plusDays(secondRecognitionOffset)));
  }

}
