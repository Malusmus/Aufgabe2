package de.dis2011;

import java.util.Date;
import java.util.ArrayList;

public interface UseCaseHandler {
	
	public void checkPasswordForMakler(String maklerName);
	
	public void createAccount(String maklerName, String maklerPassword);
	
	public void createEstate(int id, String city, String postalCode, String street, int streetNumber, int squareArea);
	
	public void createApartment(int id, int floor, int rent, int rooms, boolean balcony, boolean kitchen);
	
	public void createHouse(int id, int floors, int price, boolean garden);
	
	public void deleteEstate(int id);
	
	public void updateEstate(int id, String city, String postalCode, String street, int streetNumber, int squareArea);
	
	public void updateApartment(int id, int floor, int rent, int rooms, boolean balcony, boolean kitchen);

	public void updateHouse(int id, int floors, int price, boolean garden);

	public void createContactGeneral(int number, Estate e, Date d, String p);
	
	public void createTenancyContactSpecifics(int number, int estateNumber, Date startDate, Date duration, int additionalCosts);
	
	public void createPurchaseContractSpecifics(int number, int estateNumber, int numberInstallments, int interestRate);
	
	public void updateContactGeneral(int number, Estate e, Date d, String p);
	
	public void updateTenancyContactSpecifics(int number, int estateNumber, Date startDate, Date duration, int additionalCosts);
	
	public void updatePurchaseContractSpecifics(int number, int estateNumber, int numberInstallments, int interestRate);
	
	public void insertPerson();
	
	public ArrayList<Contract> getAllContracts();
	
	public ArrayList<Estate> getAllEstates();
	
}
