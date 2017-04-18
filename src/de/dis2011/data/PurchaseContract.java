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

    private int NoOfInstallments;
    private double InterestRate;

    public int getNoofinstallments() {
        return NoOfInstallments;
    }

    public void setNoofinstallments(int NoOfInstallments) {
        this.NoOfInstallments = NoOfInstallments;
    }

    public double getInterestrate() {
        return InterestRate;
    }

    public void setInterestrate(double InterestRate) {
        this.InterestRate = InterestRate;
    }
}
