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
public abstract class Estate {

    private int estateId;
    private int estateAgentId;
    private String city;
    private String postalCode;
    private String street;
    private String streetNumber;
    private double squareArea;

    public Estate( int id, int estateAgentId, String city, String postalCode, String street, String streetNumber, double squareArea){
     this.estateId = id;
     this.estateAgentId = estateAgentId;
     this.city = city;
     this.postalCode = postalCode;
     this.street = street;
     this.streetNumber = streetNumber;
     this.squareArea = squareArea;
     }
    
    
    public int getEstateId() {
        return estateId;
    }

    public int getEstateAgentId() {
        return estateAgentId;
    }

    public void setEstateAgentId(int estateAgentId) {
        this.estateAgentId = estateAgentId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public double getSquareArea() {
        return squareArea;
    }

    public void setSquareArea(double squareArea) {
        this.squareArea = squareArea;
    }

}
