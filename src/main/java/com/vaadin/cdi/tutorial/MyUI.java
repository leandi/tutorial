package com.vaadin.cdi.tutorial;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.cdi.CDIUI;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import javax.inject.Inject;

@Theme("valo")
@CDIUI("")
@SuppressWarnings("serial")
public class MyUI extends UI {
    
    @Inject
    private UserInfo user;
    @Inject
    private Greeting greeting;
    
    @Override
    protected void init(VaadinRequest vaadinRequest) {
        user.setName("Sandi hehe");
        final VerticalLayout layout = new VerticalLayout();
        //final TextField name = new TextField();
        //name.setCaption("Type your name here:");

        Button button = new Button("Click Me");
        button.addClickListener( e -> {
//            layout.addComponent(new Label("Thanks " + name.getValue() 
//                    + ", it works!"));
            layout.addComponent(new Label(greeting.getText()));
        });
        
        layout.addComponents(button);
        layout.setMargin(true);
        layout.setSpacing(true);
        
        setContent(layout);
    }

}
