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
public class Apartment extends Estate {

	private int floor;
	private int rent;
	private int rooms;
	private boolean hasBalcony;
	private boolean builtInKitchen;

	public Apartment() {
	}

	public Apartment(int estateId, int estateAgentId, String city, String postalCode, String street,
			String streetNumber, int squareArea, int floor, int rent, int rooms, boolean hasBalcony,
			boolean builtInKitchen) {
		super(estateId, estateAgentId, city, postalCode, street, streetNumber, squareArea);
		this.floor = floor;
		this.rent = rent;
		this.rooms = rooms;
		this.hasBalcony = hasBalcony;
		this.builtInKitchen = builtInKitchen;
	}

	public int getFloor() {
		return floor;
	}

	public void setFloor(int floor) {
		this.floor = floor;
	}

	public int getRent() {
		return rent;
	}

	public void setRent(int rent) {
		this.rent = rent;
	}

	public int getRooms() {
		return rooms;
	}

	public void setRooms(int rooms) {
		this.rooms = rooms;
	}

	public boolean isHasBalcony() {
		return hasBalcony;
	}

	public void setHasBalcony(boolean hasBalcony) {
		this.hasBalcony = hasBalcony;
	}

	public boolean isBuiltInKitchen() {
		return builtInKitchen;
	}

	public void setBuiltInKitchen(boolean builtInKitchen) {
		this.builtInKitchen = builtInKitchen;
	}

	@Override
	public String getTableNameRead() {
		return "Apartment JOIN Estate ON (Apartment.EstateId = Estate.Id)";
	}

	@Override
	public String getTableNameUpdate() {
		return "apartment";
	}

	@Override
	protected Map<String, Object> getValues() {
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("floor", floor);
		values.put("rent", rent);
		values.put("rooms", rooms);
		values.put("hasBalcony", hasBalcony ? 1 : 0);
		values.put("builtInKitchen", builtInKitchen ? 1 : 0);
		values.put("estateId", getEstateId());

		return values;
	}

	@Override
	protected void fill(ResultSet res) throws SQLException {
		super.fill(res);

		floor = res.getInt("floor");
		rent = res.getInt("rent");
		rooms = res.getInt("rooms");
		hasBalcony = res.getBoolean("hasBalcony");
		builtInKitchen = res.getBoolean("builtInKitchen");
	}

	@Override
	protected ADatabaseElement preUpdate() {
		Estate result = new Estate();
		result.setCity(getCity());
		result.setEstateAgentId(getEstateAgentId());
		result.setEstateId(getEstateId());
		result.setPostalCode(getPostalCode());
		result.setSquareArea(getSquareArea());
		result.setStreet(getStreet());
		result.setStreetNumber(getStreetNumber());

		return result;
	}

	public String toString(){
	    return "Apartment " + street +" "+ streetNumber + ", " + city +  "(Id: " + id + ")";

	}
	
}
