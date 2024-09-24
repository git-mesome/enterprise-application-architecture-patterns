package io.wisoft.patterns_of_enterprise_application_architecture.domainlogic.servicelayer;

import io.wisoft.patterns_of_enterprise_application_architecture.base.money.Money;
import io.wisoft.patterns_of_enterprise_application_architecture.domainlogic.domainmodel.Contract;

import java.time.LocalDate;

/**
 * 애플리케이션 로직 처리
 * - 계약서의 수익 인식 계산
 * - 계산과 관련된 작업
 */
public class RecognitionService extends ApplicationService{
//  public void calculateRevenueRecognitions(long contractNumber) {
//    Contract contract = Contract.readForUpdate(contractNumber);
//
//    //작업의 일부를 도메인 객체 Contract 에 위임
//    contract.calculateRecognitions();
//
//    // 책임 분산
//    getEmailGateway().sendEmailMessage(
//        contract.getAdministratorEmailAddress(),
//        "RE: Contract #" + contractNumber,
//        contract + " has had revenue recognitions calculated."
//    );
//
//    // 책임 분산
//    getIntegrationGateway().publishRevenueRecognitionCalculation(contract);
//  }
//
//  public Money recognizedRevenue(long contractNumber, LocalDate asOf) {
//
//    //작업의 일부를 도메인 객체 Contract 에 위임
//    return Contract.read(contractNumber).recognizedRevenue(asOf);
//  }
}
