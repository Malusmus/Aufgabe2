package de.dis2011;

import java.util.Date;

import de.dis2011.data.Apartment;
import de.dis2011.data.Contract;
import de.dis2011.data.Estate;
import de.dis2011.data.EstateAgent;
import de.dis2011.data.House;
import de.dis2011.data.Person;
import de.dis2011.data.PurchaseContract;
import de.dis2011.data.Rents;
import de.dis2011.data.Sells;
import de.dis2011.data.TenancyContract;

import java.util.ArrayList;

public interface UseCaseHandler {

	public boolean checkPasswordForMakler(String login, String maklerPassword);

	public void createAccount(String login, String maklerName, String address, String maklerPassword);

	public void createApartment(int id, int estateAgentId, String city, String postalCode, String street, String streetNumber, int squareArea, int floor, int rent, int rooms, boolean balcony, boolean kitchen);

	public void createHouse(int id, int estateAgentId, String city, String postalCode, String street, String streetNumber, int squareArea, int floors, int price, boolean garden);

	public void deleteEstate(int id);

	public void updateEstate(int id, String city, String postalCode, String street, String streetNumber, int squareArea);

	public void updateApartment(int id, int floor, int rent, int rooms, boolean balcony, boolean kitchen);

	public void updateHouse(int id, int floors, int price, boolean garden);

	public void updateContractGeneral(int number, Date d, String p);

	public void updateTenancyContractSpecifics(int number, Date startDate, Date duration,
			int additionalCosts);

	public void updatePurchaseContractSpecifics(int number, int numberInstallments, int interestRate);

	public ArrayList<Contract> getAllContracts();

	public ArrayList<Estate> getAllEstates();

	public ArrayList<EstateAgent> getAllEstateAgents();

	public ArrayList<Person> getAllCustomers();

	public ArrayList<Sells> getAllSales();

	public ArrayList<Rents> getAllRentings();

	void insertPerson(Person person, int contractNo);
	
	void createSell(PurchaseContract pc, House h, Person p);
	
	void createVermietung(TenancyContract tc, Apartment a, Person p);

	void createPurchaseContractSpecifics(int number, Date d, String p, int numberInstallments,
			int interestRate);

	void createTenancyContractSpecifics(int number, Date d, String p, Date startDate, Date duration,
			int additionalCosts);
}
