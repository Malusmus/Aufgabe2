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

import java.util.ArrayList;

public interface UseCaseHandler {

	public boolean checkPasswordForMakler(String login, String maklerPassword);

	public void createAccount(String login, String maklerName, String address, String maklerPassword);

	public HashMap<Integer, String> getEstateAgentNamesAndIDs();

	
}
