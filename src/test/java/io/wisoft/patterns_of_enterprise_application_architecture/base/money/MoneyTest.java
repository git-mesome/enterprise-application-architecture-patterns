package io.wisoft.patterns_of_enterprise_application_architecture.base.money;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class MoneyTest {

  @Test
  @DisplayName("반올림 할당")
  void testAllocate() {
    long[] allocation = {3, 7};
    Money[] result = Money.dollars(BigDecimal.valueOf(0.05)).allocate(allocation);
    assertEquals(Money.dollars(BigDecimal.valueOf(0.02)), result[0]);
    assertEquals(Money.dollars(BigDecimal.valueOf(0.03)), result[1]);
  }

}