package io.wisoft.patterns_of_enterprise_application_architecture.domainlogic.domainmodel;

abstract class RecognitionStrategy {
  abstract void calculateRevenueRecognitions(Contract contract);
}
