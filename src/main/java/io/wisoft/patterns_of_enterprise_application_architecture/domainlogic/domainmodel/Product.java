package io.wisoft.patterns_of_enterprise_application_architecture.domainlogic.domainmodel;

public class Product {

  private String name;
  private RecognitionStrategy recognitionStrategy;

  public Product(String name, RecognitionStrategy recognitionStrategy) {
    this.name = name;
    this.recognitionStrategy = recognitionStrategy;
  }

  public static Product newWordProcessor(String name) {
    return null;
  }

  public static Product newSpreadsheet(String name) {
    return new Product(name, new ThreeWayRecognitionStrategy(60, 90));
  }

  public static Product newDatabase(String name) {
    return new Product(name, new ThreeWayRecognitionStrategy(30, 60));
  }

  public void calculateRevenueRecognitions(Contract contract) {
    recognitionStrategy.calculateRevenueRecognitions(contract);
  }
}
