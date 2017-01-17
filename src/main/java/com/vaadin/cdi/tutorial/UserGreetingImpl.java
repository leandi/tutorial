/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vaadin.cdi.tutorial;

import javax.enterprise.inject.Alternative;
import javax.inject.Inject;

/**
 *
 * @author sandi
 */
@Alternative
public class UserGreetingImpl implements Greeting {
    
    @Inject
    private UserInfo user;
    
    @Override
    public String getText() {
       return "Hello " +  user.getName() + "!!!";
    }
    
}
