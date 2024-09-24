package io.wisoft.patterns_of_enterprise_application_architecture.base.registry.sigleton;

public class PersonFinderStub {
  public Person find(long id) {
    if (id == 1) {
      return new Person("Fowler", "Martin", 10);
    }
    throw new IllegalArgumentException("Can't find id: " + String.valueOf(id));
  }
}
