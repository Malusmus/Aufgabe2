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
public class Apartment extends Estate {

	private int floor;
	private int rent;
	private int rooms;
	private boolean hasBalcony;
	private boolean builtInKitchen;

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

}
