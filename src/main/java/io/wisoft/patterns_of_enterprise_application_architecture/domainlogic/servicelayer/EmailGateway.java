package io.wisoft.patterns_of_enterprise_application_architecture.domainlogic.servicelayer;

public interface EmailGateway {
  void sendEmailMessage(String toAddress, String subject, String body);
}
