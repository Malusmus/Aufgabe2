/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dis2011;

/**
 *
 * @author aossa
 */
public class Test {

    public void main(String[] args) {
        HandlerImpl impl = new HandlerImpl();
        impl.checkPasswordForMakler("derChuck", "passwort123");
        impl.createAccount("test", "Testmakler", "Teststraße", "passwort321");
        impl.fireEstateAgent(3);

    }
}
