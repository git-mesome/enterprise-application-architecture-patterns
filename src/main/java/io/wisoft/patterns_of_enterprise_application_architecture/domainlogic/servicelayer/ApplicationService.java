package io.wisoft.patterns_of_enterprise_application_architecture.domainlogic.servicelayer;

/**
 * 계층 상위 타입
 */
public class ApplicationService {
  protected EmailGateway getEmailGateway() {
    //EmailGateway 인스턴스 반환
    return null;
  }

  protected IntegrationGateway getIntegrationGateway() {
    //IntegrationGateway 인스턴스 반환
    return null;
  }
}
