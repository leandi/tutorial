/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vaadin.cdi.tutorial;

import java.io.Serializable;
import javax.enterprise.inject.Default;

/**
 *
 * @author sandi
 */
@Default
public class SimpleGreetingImpl implements Greeting, Serializable{
    @Override
    public String getText() {
        return "Hello Sandi !!!";
    }
}
