package de.dis2011.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.UUID;

import com.ibm.db2.jcc.b.id;

/**
 *
 * @author aossa
 */
public class EstateAgent extends ADatabaseElement {

    private int estateAgentId;
    private String name;
    private String address;
    private String login;
    private String password;

    public EstateAgent() {
    }

    public EstateAgent(int id, String name, String address, String login, String password) {
        this.estateAgentId = id;
        this.name = name;
        this.address = address;
        this.login = login;
        this.password = password;
    }

    public int getEstateAgentId() {
        return estateAgentId;
    }

    public void setEstateAgentId(int estateAgentId) {
        this.estateAgentId = estateAgentId;
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

    @Override
    protected void fill(ResultSet res) throws SQLException {
        estateAgentId = res.getInt("estateAgentId");
        name = res.getString("name");
        address = res.getString("address");
        login = res.getString("login");
        password = res.getString("password");
    }

    @Override
    protected Map<String, Object> getIds() {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("estateAgentId", estateAgentId);

        return result;
    }

    @Override
    protected Map<String, Object> getValues() {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("name", name);
        result.put("address", address);
        result.put("login", login);
        result.put("password", password);

        return result;
    }

    @Override
    public String getTableNameUpdate() {
        return "estateAgent";
    }

    public String toString() {
        return name + " (" + estateAgentId + ")";
    }

    @Override
    protected ADatabaseElement preUpdate() {
        return null;
    }

}
