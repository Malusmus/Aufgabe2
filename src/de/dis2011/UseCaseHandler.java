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
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public interface UseCaseHandler {

    public boolean checkPasswordForMakler(String login, String maklerPassword);

    public void createAccount(String login, String maklerName, String address, String maklerPassword);

    public ArrayList<EstateAgent> getEstateAgents();

    public void fireEstateAgent(int ID);

    public void createPerson(String name, String vorname, String adresse);

    public ArrayList<Person> getPersonen();

    public void killPerson(int id);

    public void createApartment(int estateAgentId, String city, String postalCode, String street,
            String streetNumber, double squareArea,
            int floor, int rent, int rooms, boolean hasBalcony, boolean builtInKitchen);

    public void createHouse(int estateAgentId, String city, String postalCode,
            String street, String streetNumber,
            double squareArea,
            int floors, int price, boolean hasGarden);

    public ArrayList<House> getHouses();

    public ArrayList<Apartment> getApartments();

    public void killHouse(int id);

    public void killApartment(int id);
    
    public ArrayList<Contract> getContracts();
    
    public void createContract(Contract c);
    
    public void deleteContract(int contractID);
    
    
}
