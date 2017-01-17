/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vaadin.cdi.tutorial;

import com.vaadin.cdi.UIScoped;
import java.io.Serializable;

/**
 *
 * @author sandi
 */
@UIScoped
public class UserInfo implements Serializable {
    private String name;

    public UserInfo() {
        this.name = "Neznanec";
    }

    public UserInfo(String name) {
        this.name = name;
    }
    
        public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    

    
}
