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

        newAccount.setEstateAgentId((int) ids.get("estateAgentId"));
        newAccount.setLogin(login);
        newAccount.setName(maklerName);
        newAccount.setAddress(address);
        newAccount.setPassword(maklerPassword);
        newAccount.create();
    }

    @Override
    public HashMap<Integer, String> getEstateAgentNamesAndIDs() {
        return null;
    }

    @Override
    public void fireEstateAgent(int ID) {
        EstateAgent account = new EstateAgent();
        account.setEstateAgentId(ID);
        account.delete();
    }

}
