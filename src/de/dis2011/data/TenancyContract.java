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
public class TenancyContract {

    private java.util.Date StartDate;
    private java.util.Date Duration;
    private double AdditionalCosts;

    public java.util.Date getStartdate() {
        return StartDate;
    }

    public void setStartdate(java.util.Date StartDate) {
        this.StartDate = StartDate;
    }

    public java.util.Date getDuration() {
        return Duration;
    }

    public void setDuration(java.util.Date Duration) {
        this.Duration = Duration;
    }

    public double getAdditionalcosts() {
        return AdditionalCosts;
    }

    public void setAdditionalcosts(double AdditionalCosts) {
        this.AdditionalCosts = AdditionalCosts;
    }
}
