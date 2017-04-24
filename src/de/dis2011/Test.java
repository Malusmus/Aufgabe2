/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dis2011;

import de.dis2011.data.EstateAgent;
import de.dis2011.data.Person;
import java.util.ArrayList;

/**
 *
 * @author aossa
 */
public class Test {
    
    public static void main(String[] args) {
        HandlerImpl impl = new HandlerImpl();
        ArrayList<EstateAgent> agents = impl.getEstateAgents();
        impl.checkPasswordForMakler("derChuck", "passwort123");
        impl.createAccount("test", "Testmakler", "Teststraße", "passwort321");
        impl.fireEstateAgent(3);
        
        ArrayList<Person> persons = impl.getPersonen();
        impl.createPerson("Test", "Testvorname", "Testadresse");
        
    }
}
