package io.wisoft.patterns_of_enterprise_application_architecture.base.money;


import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;
import java.util.Objects;

public class Money {
  private long amount;
  private Currency currency;

  public Money(double amount, Currency currency) {
    this.amount = Math.round(amount * centFactor());
    this.currency = currency;
  }

  public Money(long amount, Currency currency) {
    this.amount = amount * centFactor();
    this.currency = currency;
  }

  /**
   * 금액의 가장 작은 기본 단위 정수 액수
   * 10의 거듭제곱 활용하여 주요 단위 안에 보조 단위 개수 파악
   * Java 에서는 배열 활용
   */
  private static final int[] cents = new int[]{1, 10, 100, 1000};


  private int centFactor() {
    return cents[currency.getDefaultFractionDigits()];
  }

  public BigDecimal amount() {
    return BigDecimal.valueOf(amount, currency.getDefaultFractionDigits());
  }

  public Currency currency() {
    return currency;
  }

  public static Money dollars(BigDecimal amount) {
    return new Money(amount, Currency.getInstance("USD"));
  }

  //동등성 비교
  public boolean equals(Object other) {
    return (other instanceof Money) && equals((Money) other);
  }

  public boolean equals(Money other) {
    return currency.equals(other.currency) && amount == other.amount;
  }

  @Override
  public int hashCode() {
    return Objects.hash(amount, currency);
  }

  //산술연산
  //더하기
  public Money add(Money other) {
    assertSameCurrencyAs(other);
    return newMoney(amount + other.amount);
  }

  private void assertSameCurrencyAs(Money arg) {
    Assert.isTrue(this.currency.equals(arg.currency), "Money math mismatch: currencies are different");
  }

  private Money newMoney(long amount) {
    return new Money(amount, currency);
  }

  //빼기
  public Money substract(Money other) {
    assertSameCurrencyAs(other);
    return newMoney(amount - other.amount);
  }

  //비교
  public int compareTo(Money other) {
    assertSameCurrencyAs(other);
    return Long.compare(amount, other.amount);
  }

  //double 타입의 금액으로 곱셈
  public Money multiply(double amount) {
    return multiply(new BigDecimal(amount));
  }

  //BigDecimal 타입의 금액으로 곱셈, 기본 만올림 모드 사용
  public Money multiply(BigDecimal amount) {
    return multiply(amount, RoundingMode.HALF_EVEN.ordinal());
  }

  //여기 다시봐봐
  public Money(BigDecimal multiply, Currency currency, int roundingMode) {
  }

  // 사용자 정의 반올림 모드
  public Money multiply(BigDecimal amount, int roundingMode) {
    return new Money(amount().multiply(amount), currency, roundingMode);
  }

  //할당 메서드 - 동일한 액수를 여러 대상에 할당
  public Money[] allocate(int n) {
    Money lowResult = newMoney(amount / n);
    Money highResult = newMoney(lowResult.amount + 1);
    Money[] result = new Money[n];
    int remainder = (int) (amount % n);
    for (int i = 0; i < remainder; i++) {
      result[i] = highResult;
    }
    for (int i = remainder; i < n; i++) {
      result[i] = lowResult;
    }
    return result;
  }

  //할당 메서드 - 모든 비율 처리
  public Money[] allocate(long[] ratios) {
    long total = 0;
    for (int i = 0; i < ratios.length; i++) {
      total += ratios[i];
    }
    long remainder = amount;
    Money[] results = new Money[ratios.length];
    for (int i = 0; i < results.length; i++) {
      results[i] = newMoney(amount * ratios[i] / total);
      remainder -= results[i].amount;
    }
    for (int i = 0; i < remainder; i++) {
      results[i].amount++;
    }
    return results;
  }


}