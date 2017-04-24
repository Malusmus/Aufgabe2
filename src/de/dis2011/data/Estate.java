/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dis2011.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author aossa
 */
public class Estate extends ADatabaseElement {

    private int estateId;
    private int estateAgentId;
    private String city;
    private String postalCode;
    private String street;
    private String streetNumber;
    private double squareArea;

    public Estate() {
    }

    public Estate(int id, int estateAgentId, String city, String postalCode, String street, String streetNumber, double squareArea) {
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

    @Override
    public String getTableNameUpdate() {
        return "estate";
    }

    @Override
    protected Map<String, Object> getIds() {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("estateId", estateId);

        return result;
    }

    @Override
    protected Map<String, Object> getValues() {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("estateAgentId", estateAgentId);
        result.put("city", city);
        result.put("postalCode", postalCode);
        result.put("street", street);
        result.put("streetNumber", streetNumber);
        result.put("squareArea", squareArea);

        return result;
    }

    @Override
    protected void fill(ResultSet res) throws SQLException {
        estateId = res.getInt("estateId");
        estateAgentId = res.getInt("estateAgentId");
        city = res.getString("city");
        postalCode = res.getString("postalCode");
        street = res.getString("street");
        streetNumber = res.getString("streetNumber");
        squareArea = res.getDouble("squareArea");
    }

}
