/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dis2011.data;

import java.awt.event.TextEvent;

/**
 *
 * @author aossa
 */
public class Rents {

	
    //private int tenancyContractNo;
    //private int apartmentId;
    //private int personId;
    private TenancyContract tenancy;
    private Apartment flat;
    private Person renter;

   public Rents(TenancyContract tenancy, Apartment flat, Person renter){
	   this.tenancy = tenancy;
	   this.flat = flat;
	   this.renter = renter;
   }
   
   public void setContract(TenancyContract c){
	   tenancy = c;
   }

   public TenancyContract getContract(){
	   return tenancy;
   }
   
   public void setApartment(Apartment a){
	   flat = a;
   }
   
   public Apartment getApartment(){
	   return flat;
   }
   
   public void setPerson(Person p){
	   renter = p;
   }
 
   public Person getPerson(){
	   return renter;
   }
   
   public String toString(){
 		return renter.getName() + " " + flat.toString() + " " + tenancy.getDate().toString();
     }
}
