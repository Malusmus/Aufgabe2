package de.dis2011.data;

import java.util.Timer;
import java.util.UUID;

import com.ibm.db2.jcc.b.id;

/**
 *
 * @author aossa
 */
public class EstateAgent {

	private int estateAgentId;
	private String name;
	private String address;
	private String login;
	private String password;

	public EstateAgent(String name, String address, String login, String password) {
		createId();
		this.name = name;
		this.address = address;
		this.login = login;
		this.password = password;
	}
	
	public EstateAgent(int id, String name, String address, String login, String password) {
		estateAgentId = id;
		this.name = name;
		this.address = address;
		this.login = login;
		this.password = password;
	}

	public void createId() {
		estateAgentId = (int) Math.random() * 10000000;
	}

	public int getEstateAgentId() {
		return estateAgentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String toString(){
		return name + " (" + estateAgentId + ")";
	}
}
