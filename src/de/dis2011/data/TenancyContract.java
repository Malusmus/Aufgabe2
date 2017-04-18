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
public class TenancyContract {

    private int contractNo;
    private Date startDate;
    private Date duration;
    private double additionalCosts;

    public int getContractNo() {
        return contractNo;
    }

    public void setContractNo(int contractNo) {
        this.contractNo = contractNo;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getDuration() {
        return duration;
    }

    public void setDuration(Date duration) {
        this.duration = duration;
    }

    public double getAdditionalCosts() {
        return additionalCosts;
    }

    public void setAdditionalCosts(double additionalCosts) {
        this.additionalCosts = additionalCosts;
    }

}
