/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dis2011;

import de.dis2011.data.ADatabaseElement;
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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author aossa
 */
public class HandlerImpl implements UseCaseHandler {

	@Override
	public boolean checkPasswordForMakler(String login, String maklerPassword) {
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("login", login);
		parameter.put("password", maklerPassword);

		List<EstateAgent> agents = ADatabaseElement.loadAll(EstateAgent.class, parameter);

		return agents != null && agents.size() > 0;
	}

	@Override
	public void createAccount(String login, String maklerName, String address, String maklerPassword) {
		EstateAgent newAccount = new EstateAgent();
		Map<String, Object> ids = newAccount.makeId();

		newAccount.setEstateAgentId((Integer) ids.get("estateAgentId"));
		newAccount.setLogin(login);
		newAccount.setName(maklerName);
		newAccount.setAddress(address);
		newAccount.setPassword(maklerPassword);
		newAccount.create();
	}

	@Override
	public void fireEstateAgent(int ID) {
		EstateAgent account = new EstateAgent();
		account.setEstateAgentId(ID);
		account.delete();
	}

	@Override
	public ArrayList<EstateAgent> getEstateAgents() {
		return (ArrayList) ADatabaseElement.loadAll(EstateAgent.class, null);
	}

	@Override
	public void createPerson(String name, String vorname, String adresse) {
		Person person = new Person();
		Map<String, Object> ids = person.makeId();

		person.setId((Integer) ids.get("id"));
		person.setAddress(adresse);
		person.setFirstName(vorname);
		person.setName(name);

		person.create();
	}

	@Override
	public ArrayList<Person> getPersonen() {
		return (ArrayList) ADatabaseElement.loadAll(Person.class, null);
	}

	@Override
	public void killPerson(int id) {
		Person person = new Person();
		person.setId(id);

		person.delete();
	}

	@Override
	public void createApartment(int estateAgentId, String city, String postalCode, String street, String streetNumber,
			double squareArea, int floor, int rent, int rooms, boolean hasBalcony, boolean builtInKitchen) {
		Apartment newApartment = new Apartment();
		Map<String, Object> ids = newApartment.makeId();

		newApartment.setEstateId((Integer) ids.get("id"));
		newApartment.setBuiltInKitchen(builtInKitchen);
		newApartment.setCity(city);
		newApartment.setEstateAgentId(estateAgentId);
		newApartment.setFloor(floor);
		newApartment.setHasBalcony(hasBalcony);
		newApartment.setPostalCode(postalCode);
		newApartment.setRent(rent);
		newApartment.setRooms(rooms);
		newApartment.setSquareArea(squareArea);
		newApartment.setStreet(street);
		newApartment.setStreetNumber(streetNumber);

		newApartment.create();
	}

	@Override
	public void createHouse(int estateAgentId, String city, String postalCode, String street, String streetNumber,
			double squareArea, int floors, int price, boolean hasGarden) {
		throw new UnsupportedOperationException("Not supported yet."); // To
																		// change
																		// body
																		// of
																		// generated
																		// methods,
																		// choose
																		// Tools
																		// |
																		// Templates.
	}

	@Override
	public ArrayList<House> getHouses() {
		throw new UnsupportedOperationException("Not supported yet."); // To
																		// change
																		// body
																		// of
																		// generated
																		// methods,
																		// choose
																		// Tools
																		// |
																		// Templates.
	}

	@Override
	public ArrayList<Apartment> getApartments() {
		throw new UnsupportedOperationException("Not supported yet."); // To
																		// change
																		// body
																		// of
																		// generated
																		// methods,
																		// choose
																		// Tools
																		// |
																		// Templates.
	}

	@Override
	public void killHouse(int id) {
		throw new UnsupportedOperationException("Not supported yet."); // To
																		// change
																		// body
																		// of
																		// generated
																		// methods,
																		// choose
																		// Tools
																		// |
																		// Templates.
	}

	@Override
	public void killApartment(int id) {
		throw new UnsupportedOperationException("Not supported yet."); // To
																		// change
																		// body
																		// of
																		// generated
																		// methods,
																		// choose
																		// Tools
																		// |
																		// Templates.
	}

	@Override
	public ArrayList<Contract> getContracts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createContract(Contract c) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteContract(int contractID) {
		// TODO Auto-generated method stub
	}

	@Override
	public void createSale(Sells sale) {
		// TODO Auto-generated method stub

	}

	@Override
	public void createRent(Rents rent) {
		// TODO Auto-generated method stub

	}
}
