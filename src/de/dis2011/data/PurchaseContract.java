/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dis2011.data;

/**
 *
 * @author aossa
 */
public class PurchaseContract {

    private int contractNo;
    private int NoOfInstallments;
    private double InterestRate;

    public int getContractNo() {
        return contractNo;
    }

    public void setContractNo(int contractNo) {
        this.contractNo = contractNo;
    }

    public int getNoOfInstallments() {
        return NoOfInstallments;
    }

    public void setNoOfInstallments(int NoOfInstallments) {
        this.NoOfInstallments = NoOfInstallments;
    }

    public double getInterestRate() {
        return InterestRate;
    }

    public void setInterestRate(double InterestRate) {
        this.InterestRate = InterestRate;
    }

}
