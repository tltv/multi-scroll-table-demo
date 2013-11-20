package org.vaadin.tltv.multiscrolltable.demo;

import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;

/**
 * The Application's "main" class
 */
@SuppressWarnings("serial")
public class MyVaadinUI extends UI {

    private VerticalLayout layout;

    @Override
    protected void init(VaadinRequest request) {
        getPage().setTitle("MultiScrollTable Demo");

        layout = new VerticalLayout();

        setContent(layout);

        reset();
    }

    public void reset() {
        layout.removeAllComponents();

        TabSheet tabsheet = new TabSheet();
        tabsheet.setSizeFull();
        tabsheet.addTab(new DynamicHeaderDemo(), "Dynamic headers", null);
        tabsheet.addTab(new MultiScrollContentsDemo(),
                "Multiple scroll contents", null);

        layout.addComponent(createResetButton());
        layout.addComponent(tabsheet);
    }

    private Button createResetButton() {
        Button b = new Button("Reset");
        b.setStyleName(Reindeer.BUTTON_LINK);
        b.addClickListener(new ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                reset();
            }
        });
        return b;
    }
}
