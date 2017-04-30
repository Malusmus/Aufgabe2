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
public class House extends Estate{

    private int floors;
    private int price;
    private boolean hasGarden;

    public House(int estateId, int estateAgentId, String city, String postalCode, String street, String streetNumber, int squareArea, int floors, int price, boolean hasGarden){
		super(estateId, estateAgentId, city, postalCode, street, streetNumber, squareArea);
	    this.floors = floors;
	    this.price = price;
	    this.hasGarden = hasGarden;
	}


	public int getFloors() {
        return floors;
    }

    public void setFloors(int floors) {
        this.floors = floors;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isHasGarden() {
        return hasGarden;
    }

    public void setHasGarden(boolean hasGarden) {
        this.hasGarden = hasGarden;
    }
    
	public String toString(){
	    return "Haus " + street +" "+ streetNumber + ", " + city +  "(Id: " + id + ")";

	}

}
