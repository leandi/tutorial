package com.vaadin.cdi.tutorial;

import javax.inject.Inject;

import com.vaadin.cdi.CDIView;
import com.vaadin.cdi.internal.Conventions;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@CDIView
public class ChatView extends CustomComponent implements View {

    @Inject
    private UserDAO userDAO;

    @Inject
    private UserInfo userInfo;
	
    private User targetUser;

    @Override
    public void enter(ViewChangeEvent event) {
        String parameters = event.getParameters();
        Layout layout;
        if (parameters.isEmpty()) {
			targetUser = null;
            layout = buildUserSelectionLayout();
        } else {
            targetUser = userDAO.getUserBy(parameters);
            if (targetUser == null) {
                layout = buildErrorLayout();
            } else {
                layout = buildUserLayout();
            }
        }
        setCompositionRoot(layout);

    }

    private Layout buildErrorLayout() {
        VerticalLayout layout = new VerticalLayout();
        layout.setWidth("100%");
        layout.setMargin(true);
        layout.setSpacing(true);
        layout.addComponent(new Label("No such user"));
        layout.addComponent(generateBackButton());
        return layout;
    }

    private Layout buildUserLayout() {
        VerticalLayout layout = new VerticalLayout();
        layout.setWidth("100%");
        layout.setMargin(true);
        layout.setSpacing(true);
        layout.addComponent(new Label("Talking to " + targetUser.getName()));
        layout.addComponent(generateBackButton());
        return layout;
    }

    private Layout buildUserSelectionLayout() {
        VerticalLayout layout = new VerticalLayout();
        layout.setWidth("100%");
        layout.setMargin(true);
        layout.setSpacing(true);
        layout.addComponent(new Label("Select user to talk to:"));
        for (User user : userDAO.getUsers()) {
            if (user.equals(userInfo.getUser())) {
                continue;
            }
            layout.addComponent(generateUserSelectionButton(user));
        }
        return layout;
    }

    private Button generateBackButton() {
        Button button = new Button("Back");
        button.addClickListener(new ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                UI ui = getUI();
                if (ui != null) {
                    Navigator navi = ui.getNavigator();
                    if (navi != null) {
                        navi.navigateTo(Conventions
                                .deriveMappingForView(ChatView.class));
                    }
                }
            }
        });
        return button;
    }

    private Button generateUserSelectionButton(final User user) {
        Button button = new Button(user.getName());
        button.addClickListener(new ClickListener() {

            @Override
            public void buttonClick(ClickEvent event) {
                UI ui = getUI();
                if (ui != null) {
                    Navigator navi = ui.getNavigator();
                    if (navi != null) {
                        navi.navigateTo(Conventions
                                .deriveMappingForView(ChatView.class)
                                + "/"
                                + user.getUsername());
                    }
                }
            }
        });
        return button;
    }

}