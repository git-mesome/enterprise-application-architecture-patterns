package io.wisoft.patterns_of_enterprise_application_architecture.domainlogic.domainmodel;

public class CompleteRecognitionStrategy extends RecognitionStrategy {

  @Override
  void calculateRevenueRecognitions(Contract contract) {
    contract.addRevenueRecognition(new RevenueRecognition(contract.getRevenue(), contract.getWhenSigned()));
  }

}
