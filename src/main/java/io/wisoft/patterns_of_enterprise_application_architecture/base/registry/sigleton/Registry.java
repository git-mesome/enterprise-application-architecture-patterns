package io.wisoft.patterns_of_enterprise_application_architecture.base.registry.sigleton;

import io.wisoft.patterns_of_enterprise_application_architecture.datasource.rowdatagateway.PersonFinder;

public class Registry {

  /**
   * 단일 인스턴스를 나타내는 정적 변수
   **/
  private static Registry soleInstance = new Registry();

  private static Registry getInstance() {
    return soleInstance;
  }

  /**
   * personFinder 객체를 Registry 인스턴스에 저장
   */
  protected PersonFinder personFinder = new PersonFinder();

  public static PersonFinder personFinder(){
    return getInstance().personFinder;
  }

  /**
   * 새 인스턴스 생성 시 레지스트리를 다시 초기화
   */
  public static void initialize() {
    soleInstance = new Registry();
  }

  public static void initializeStub() {
    soleInstance = new RegistryStub();
  }

  public static Object getPerson(Long id) {
    return null;
  }

}
