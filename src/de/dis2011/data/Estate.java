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
public class Estate {

    private int EstateAgentId;
    private String City;
    private String PostalCode;
    private String Street;
    private String StreetNumber;
    private double SquareArea;

    public int getEstateagentid() {
        return EstateAgentId;
    }

    public void setEstateagentid(int EstateAgentId) {
        this.EstateAgentId = EstateAgentId;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String City) {
        this.City = City;
    }

    public String getPostalcode() {
        return PostalCode;
    }

    public void setPostalcode(String PostalCode) {
        this.PostalCode = PostalCode;
    }

    public String getStreet() {
        return Street;
    }

    public void setStreet(String Street) {
        this.Street = Street;
    }

    public String getStreetnumber() {
        return StreetNumber;
    }

    public void setStreetnumber(String StreetNumber) {
        this.StreetNumber = StreetNumber;
    }

    public double getSquarearea() {
        return SquareArea;
    }

    public void setSquarearea(double SquareArea) {
        this.SquareArea = SquareArea;
    }
}
