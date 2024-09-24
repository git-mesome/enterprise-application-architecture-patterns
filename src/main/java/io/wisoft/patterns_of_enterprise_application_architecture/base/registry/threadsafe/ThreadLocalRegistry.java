package io.wisoft.patterns_of_enterprise_application_architecture.base.registry.threadsafe;


import org.springframework.util.Assert;

public class ThreadLocalRegistry {
  private static ThreadLocal instance = new ThreadLocal();

  public static ThreadLocalRegistry getInstance() {
    return (ThreadLocalRegistry) instance.get();
  }

  /**
   * 레지스트리를 얻고 해제하는 메서드에서 레지스트리 설정
   * 일반적으로 트랜잭션/세션 호출 경계에서 수행 가능
   */
  public static void begin() {
    Assert.isTrue(instance.get() == null, "Instance already begun");
    instance.set(new ThreadLocalRegistry());
  }

  public static void end() {
    Assert.notNull(getInstance(), "Instance not set");
    instance.set(null);
  }

  /**
   * 인스턴스 저장
   */
  private PersonFinder personFinder = new PersonFinder();

  public static PersonFinder personFinder() {
    return getInstance().personFinder;
  }

}
