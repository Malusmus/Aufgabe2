/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dis2011.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author aossa
 */
public class Person extends ADatabaseElement {

    private int id;
    private String firstName;
    private String name;
    private String address;

    public Person() {
    }

    public Person(int id, String firstName, String name, String address) {
        this.id = id;
        this.firstName = firstName;
        this.name = name;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String toString() {
        return firstName + " " + name + " (" + id + ")";
    }

    @Override
    protected Map<String, Object> getIds() {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("id", id);

        return result;
    }

    @Override
    protected Map<String, Object> getValues() {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("firstName", firstName);
        result.put("name", name);
        result.put("address", address);

        return result;
    }

    @Override
    public String getTableNameUpdate() {
        return "person";
    }

    @Override
    protected void fill(ResultSet res) throws SQLException {
        id = res.getInt("id");
        firstName = res.getString("firstName");
        name = res.getString("name");
        address = res.getString("address");
    }

    @Override
    protected ADatabaseElement preUpdate() {
        return null;
    }

}
