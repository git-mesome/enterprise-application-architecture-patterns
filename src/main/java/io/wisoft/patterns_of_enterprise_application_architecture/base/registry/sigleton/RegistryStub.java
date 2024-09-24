package io.wisoft.patterns_of_enterprise_application_architecture.base.registry.sigleton;

public class RegistryStub extends Registry {
  public RegistryStub() {
    personFinder = new PersonFinderStub();
  }
}

