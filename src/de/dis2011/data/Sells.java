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
public class Sells {

    private PurchaseContract purchaseContract;
    private House house;
    private Person person;

    public Sells(PurchaseContract purchaseContract, House house, Person person){
    	this.purchaseContract = purchaseContract;
    	this.house = house;
    	this.person = person;
    }
    
    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public PurchaseContract getPurchaseContract() {
        return purchaseContract;
    }

    public void setPurchaseContract(PurchaseContract purchaseContract) {
        this.purchaseContract = purchaseContract;
    }

}
