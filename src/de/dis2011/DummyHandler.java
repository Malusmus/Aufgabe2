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
		_makler.add(new EstateAgent(3, "Benjamin Blümchen", "Im Zoo", "Elefant", "123"));

		_immobilien = new ArrayList<Estate>();
		_immobilien.add(new House(1, 2, "Hamburg", "123", "Blauweg", "12", 123, 12, 17178, true));
		_immobilien.add(new House(2, 1, "Berlin", "7563", "Blauweg", "80", 7, 12, 5, false));
		_immobilien.add(new Apartment(3, 2, "Alpen", "157", "Bergweg", "17a", 1256, 7, 7, 9, false, true));
		_immobilien.add(new Apartment(4, 3, "Karnin", "456", "Bergweg", "9", 1634, 2, 8, 7, true, false));

		_kunden = new ArrayList<Person>();
		_kunden.add(new Person(1, "Anna", "Meier", "Aus dem Wald 23"));
		_kunden.add(new Person(1, "Dörte", "Bauer", "Vom Acker 46"));
		_kunden.add(new Person(1, "Viktor", "Frankenstein", "unbekannt"));

		_vertraege = new ArrayList<Contract>();
		_vertraege.add(new TenancyContract(1, new Date(2017,2,7), "Hier", new Date(1999,10,10), new Date(2000,2,3), 1313));
		_vertraege.add(new TenancyContract(2, new Date(2017,2,7), "Dort", new Date(1999,10,10), new Date(2000,2,3), 458));
		_vertraege.add(new PurchaseContract(3, new Date(2010,1,2), "Habsburg", 7, 80.1));
		_vertraege.add(new PurchaseContract(4, new Date(2010,1,2), "Drüben", 70, 458.1));

		_verkaeufe = new ArrayList<Sells>();
		_verkaeufe.add(new Sells((PurchaseContract) _vertraege.get(2),(House) _immobilien.get(0), _kunden.get(0)));
		_verkaeufe.add(new Sells((PurchaseContract) _vertraege.get(3),(House) _immobilien.get(1), _kunden.get(1)));

		_vermietungen = new ArrayList<Rents>();
		_vermietungen.add(new Rents((TenancyContract) _vertraege.get(0),(Apartment) _immobilien.get(2), _kunden.get(2)));
		_vermietungen.add(new Rents((TenancyContract) _vertraege.get(1),(Apartment) _immobilien.get(3), _kunden.get(1)));
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
		_makler.add(new EstateAgent(maklerName, address, login, maklerPassword));
	}

	@Override
	public void deleteEstate(int id) {
		// TODO Auto-generated method stub
		for (Estate e : _immobilien) {
			if (e.getEstateId() == id) {
				_immobilien.remove(e);
			}
		}
	}

	@Override
	public void updateEstate(int id, String city, String postalCode, String street, String streetNumber,
			int squareArea) {
		for (Estate e : _immobilien) {
			if (e.getEstateId() == id) {
				e.setCity(city);
				e.setPostalCode(postalCode);
				e.setStreet(street);
				e.setStreetNumber(streetNumber);
				e.setSquareArea(squareArea);
			}
		}
	}

	@Override
	public void updateApartment(int id, int floor, int rent, int rooms, boolean balcony, boolean kitchen) {
		for (Estate e : _immobilien) {
			if (e.getEstateId() == id) {
				((Apartment) e).setFloor(floor);
				((Apartment) e).setRent(rent);
				((Apartment) e).setRooms(rooms);
				((Apartment) e).setHasBalcony(balcony);
				((Apartment) e).setBuiltInKitchen(kitchen);
			}
		}
	}

	@Override
	public void updateHouse(int id, int floors, int price, boolean garden) {
		for (Estate e : _immobilien) {
			if (e.getEstateId() == id) {
				((House) e).setFloors(floors);
				((House) e).setPrice(price);
				((House) e).setHasGarden(garden);
			}
		}
	}

	@Override
	public void createTenancyContractSpecifics(int number, Date d, String p, Date startDate, Date duration,
			int additionalCosts) {
		_vertraege.add(new TenancyContract(number, d, p, startDate, duration, additionalCosts));
	}

	@Override
	public void createPurchaseContractSpecifics(int number, Date d, String p, int numberInstallments,
			int interestRate) {
		_vertraege.add(new PurchaseContract(number, d, p, numberInstallments, interestRate));
	}

	@Override
	public void updateContractGeneral(int number, Date d, String p) {
		for (Contract c : _vertraege) {
			if (c.getContractNo() == number && c instanceof PurchaseContract) {
				c.setContractNo(number);
				c.setDate(d);
				c.setPlace(p);
			}
		}
	}

	@Override
	public void updateTenancyContractSpecifics(int number, Date startDate, Date duration, int additionalCosts) {
		for (Contract c : _vertraege) {
			if (c.getContractNo() == number && c instanceof TenancyContract) {
				((TenancyContract) c).setDate(startDate);
				((TenancyContract) c).setDuration(duration);
				((TenancyContract) c).setAdditionalCosts(additionalCosts);
			}
		}
	}

	@Override
	public void updatePurchaseContractSpecifics(int number, int numberInstallments, int interestRate) {
		for (Contract c : _vertraege) {
			if (c.getContractNo() == number) {
				((PurchaseContract) c).setNoOfInstallments(numberInstallments);
				((PurchaseContract) c).setInterestRate(interestRate);
			}
		}
	}

	@Override
	public void insertPerson(Person person, int contractNo) {
		for (Sells s : _verkaeufe) {
			if (s.getPurchaseContract().getContractNo() == contractNo) {
				s.setPerson(person);
			}
		}
		for (Rents r : _vermietungen) {
			if (r.getContract().getContractNo() == contractNo) {
				r.setPerson(person);
			}
		}
	}

	@Override
	public ArrayList<Contract> getAllContracts() {
		return _vertraege;
	}

	@Override
	public ArrayList<Estate> getAllEstates() {
		return _immobilien;
	}

	@Override
	public ArrayList<EstateAgent> getAllEstateAgents() {
		return _makler;
	}

	@Override
	public ArrayList<Person> getAllCustomers() {
		return _kunden;
	}

	@Override
	public ArrayList<Sells> getAllSales() {
		return _verkaeufe;
	}

	@Override
	public ArrayList<Rents> getAllRentings() {
		return _vermietungen;
	}

	@Override
	public void createSell(PurchaseContract pc, House h, Person p) {
		_verkaeufe.add(new Sells(pc, h, p));
	}

	@Override
	public void createVermietung(TenancyContract tc, Apartment a, Person p) {
		_vermietungen.add(new Rents(tc, a, p));
	}

	@Override
	public void createApartment(int id, int estateAgentId, String city, String postalCode, String street,
			String streetNumber, int squareArea, int floor, int rent, int rooms, boolean balcony, boolean kitchen) {
		_immobilien.add(new Apartment(id, estateAgentId, city, postalCode, street, streetNumber, squareArea, floor,
				rent, rooms, balcony, kitchen));
	}

	@Override
	public void createHouse(int id, int estateAgentId, String city, String postalCode, String street,
			String streetNumber, int squareArea, int floors, int price, boolean garden) {
		_immobilien.add(new House(id, estateAgentId, city, postalCode, street, streetNumber, squareArea, floors, price,
				garden));
	}

}
