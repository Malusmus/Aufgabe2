package de.dis2011;

import java.util.ArrayList;
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

public class DummyHandler implements UseCaseHandler {

	private ArrayList<Contract> _vertraege;
	private ArrayList<Estate> _immobilien;
	private ArrayList<EstateAgent> _makler;
	private ArrayList<Person> _kunden;
	private ArrayList<Sells> _verkaeufe;
	private ArrayList<Rents> _vermietungen;

	public DummyHandler() {
		_makler = new ArrayList<EstateAgent>();
		_makler.add(new EstateAgent(1, "Gertrude Mueller", "Unterm Sofa 7", "Gertie", "passwort1"));
		_makler.add(new EstateAgent(2, "Chuck Norris", "Universum", "Awesome", ""));
		_makler.add(new EstateAgent(3, "Benjamin Bluemchen", "Im Zoo", "Elefant", "123"));

		_immobilien = new ArrayList<Estate>();
		_immobilien.add(new House(1, 2, "Hamburg", "123", "Blauweg", "12", 123, 12, 17178, true));
		_immobilien.add(new House(2, 1, "Berlin", "7563", "Blauweg", "80", 7, 12, 5, false));
		_immobilien.add(new Apartment(3, 2, "Alpen", "157", "Bergweg", "17a", 1256, 7, 7, 9, false, true));
		_immobilien.add(new Apartment(4, 3, "Karnin", "456", "Bergweg", "9", 1634, 2, 8, 7, true, false));

		_kunden = new ArrayList<Person>();
		_kunden.add(new Person(1, "Anna", "Meier", "Aus dem Wald 23"));
		_kunden.add(new Person(1, "Doerte", "Bauer", "Vom Acker 46"));
		_kunden.add(new Person(1, "Viktor", "Frankenstein", "unbekannt"));

		_vertraege = new ArrayList<Contract>();
		_vertraege.add(new TenancyContract(1, new Date(2017, 2, 7), "Hier", new Date(1999, 10, 10),
				new Date(2000, 2, 3), 1313));
		_vertraege.add(new TenancyContract(2, new Date(2017, 2, 7), "Dort", new Date(1999, 10, 10),
				new Date(2000, 2, 3), 458));
		_vertraege.add(new PurchaseContract(3, new Date(2010, 1, 2), "Habsburg", 7, 80.1));
		_vertraege.add(new PurchaseContract(4, new Date(2010, 1, 2), "Blablub", 70, 458.1));

		_verkaeufe = new ArrayList<Sells>();
		_verkaeufe.add(new Sells((PurchaseContract) _vertraege.get(2), (House) _immobilien.get(0), _kunden.get(0)));
		_verkaeufe.add(new Sells((PurchaseContract) _vertraege.get(3), (House) _immobilien.get(1), _kunden.get(1)));

		_vermietungen = new ArrayList<Rents>();
		_vermietungen
				.add(new Rents((TenancyContract) _vertraege.get(0), (Apartment) _immobilien.get(2), _kunden.get(2)));
		_vermietungen
				.add(new Rents((TenancyContract) _vertraege.get(1), (Apartment) _immobilien.get(3), _kunden.get(1)));
	}

	@Override
	public boolean checkPasswordForMakler(String login, String maklerPassword) {
		for (EstateAgent m : _makler) {
			if (m.getLogin().equals(login)) {
				if (m.getPassword().equals(maklerPassword)) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public void createAccount(String login, String maklerName, String address, String maklerPassword) {
		int id = 0;
		boolean check = true;
		for (int i = 0; i < _makler.size(); ++i) {
			for (EstateAgent agent : _makler) {
				if (agent.getEstateAgentId() == id) {
					check = false;
				}
			}
			if (check) {
				_makler.add(new EstateAgent(id, maklerName, address, login, maklerPassword));
			} else {
				check = true;
			}
		}
	}

	@Override
	public void fireEstateAgent(int ID) {
		for (EstateAgent m : _makler) {
			if (m.getEstateAgentId() == ID) {
				_makler.remove(m);
				return;
			}
		}
	}

	@Override
	public ArrayList<EstateAgent> getEstateAgents() {
		return _makler;
	}

	public void createPerson(String name, String vorname, String adresse) {
		_kunden.add(new Person(13, vorname, name, adresse));
	}

	@Override
	public ArrayList<Person> getPersonen() {
		return _kunden;
	}

	public void killPerson(int ID) {
		for (Person p : _kunden) {
			if (p.getId() == ID) {
				_kunden.remove(ID);
				return;
			}
		}
	}

	@Override
	public void createApartment(int estateAgentId, String city, String postalCode, String street, String streetNumber,
			double squareArea, int floor, int rent, int rooms, boolean hasBalcony, boolean builtInKitchen) {

		boolean check = true;
		for (int id = 1; id <= _immobilien.size(); ++id) {
			for (Estate immobilie : _immobilien) {
				if (immobilie.getEstateId() == id) {
					check = false;
				}
			}
			if (check) {
				// FixMe Check if square area needs to be a double or int
				// LOG
				System.out.println("Vorher: " + _immobilien.size() + " / adding " + id);
				_immobilien.add(new Apartment(id, estateAgentId, city, postalCode, street, streetNumber,
						(int) squareArea, floor, rent, rooms, hasBalcony, builtInKitchen));
				// LOG
				System.out.println("Nachher: " + _immobilien.size());
				return;
			} else {
				check = true;
			}
		}
	}

	@Override
	public void createHouse(int estateAgentId, String city, String postalCode, String street, String streetNumber,
			double squareArea, int floors, int price, boolean hasGarden) {
		boolean check = true;
		for (int id = 1; id <= _immobilien.size(); ++id) {
			for (Estate immobilie : _immobilien) {
				if (immobilie.getEstateId() == id) {
					check = false;
				}
			}
			if (check) {
				// FixMe Check if square area needs to be a double or int
				// LOG
				System.out.println("Vorher: " + _immobilien.size() + " / adding " + id);
				_immobilien.add(new House(id, estateAgentId, city, postalCode, street, streetNumber, (int) squareArea,
						floors, price, hasGarden));
				// LOG
				System.out.println("Nachher: " + _immobilien.size());
				return;
			} else {
				check = true;
			}
		}
	}

	@Override
	public ArrayList<House> getHouses() {
		ArrayList<House> haeuser = new ArrayList<House>();
		for (Estate e : _immobilien) {
			if (e instanceof House) {
				haeuser.add((House) e);
			}
		}
		return haeuser;
	}

	@Override
	public ArrayList<Apartment> getApartments() {
		ArrayList<Apartment> flats = new ArrayList<Apartment>();
		for (Estate e : _immobilien) {
			if (e instanceof Apartment) {
				flats.add((Apartment) e);
			}
		}
		return flats;
	}

	@Override
	public void killHouse(int id) {
		for (Estate e : _immobilien) {
			if (e.getEstateId() == id) {
				_immobilien.remove(e);
				return;
			}
		}
	}

	@Override
	public void killApartment(int id) {
		for (Estate e : _immobilien) {
			if (e.getEstateId() == id) {
				// LOG
				System.err.println("WOHNUNGSNOT VERSCHLIMMERT!!! :(");
				_immobilien.remove(e);
				// LOG
				System.out.println("Wohnung ist weg ist " + _immobilien.contains(e));
				return;
			}
		}
	}

	@Override
	public ArrayList<Contract> getContracts() {
		return _vertraege;
	}

	@Override
	public void createContract(Contract c) {
		_vertraege.add(c);
	}

	@Override
	public void deleteContract(int contractID) {
		for (Contract c : _vertraege) {
			if (c.getContractNo() == contractID) {
				_vertraege.remove(c);
				return;
			}
		}
	}
}
