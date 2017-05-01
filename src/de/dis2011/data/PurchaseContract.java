/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dis2011.data;

import java.util.Date;

/**
 *
 * @author aossa
 */
public class PurchaseContract extends Contract {

	
    private int NoOfInstallments;
    private double InterestRate;

    public PurchaseContract(int contractNo, Date date, String place, int NoOfInstallments, double InterestRate){
    	super(contractNo, date, place);
    	this.NoOfInstallments = NoOfInstallments;
    	this.InterestRate = InterestRate;
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

    public String toString() {
		return "Verkauf " + getPlace() + ", den" + getDate();
	}
    
}
