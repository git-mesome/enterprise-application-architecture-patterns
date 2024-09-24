package io.wisoft.patterns_of_enterprise_application_architecture.domainlogic.servicelayer;

import io.wisoft.patterns_of_enterprise_application_architecture.domainlogic.domainmodel.Contract;

public interface IntegrationGateway {
  void publishRevenueRecognitionCalculation(Contract contract);
}
