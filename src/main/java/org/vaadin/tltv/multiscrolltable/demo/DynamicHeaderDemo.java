package org.vaadin.tltv.multiscrolltable.demo;

import org.vaadin.tltv.multiscrolltable.ui.CustomScrollTable;
import org.vaadin.tltv.multiscrolltable.ui.ScrollContent;

import com.vaadin.data.Container;
import com.vaadin.data.Container.Hierarchical;
import com.vaadin.data.util.HierarchicalContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class DynamicHeaderDemo extends VerticalLayout {

    private final CustomScrollTable table;

    public DynamicHeaderDemo() {
        table = new CustomScrollTable(prepareContainer());
        table.setHeight(500, Unit.PIXELS);
        table.setRowHeaderPropertyId("name");

        addComponent(table);

        GridLayout buttons = new GridLayout(3, 1);
        buttons.addComponent(createShowDefaultHeaderContentButton());
        buttons.addComponent(createShowSimpleHeaderContentButton());
        buttons.addComponent(createShowAdvancedHeaderContentButton());

        addComponent(buttons);
    }

    private Container prepareContainer() {
        Hierarchical c = new HierarchicalContainer();
        addColumnsForNewContent(c, "");
        return c;
    }

    @SuppressWarnings("unchecked")
    private void addColumnsForNewContent(Hierarchical c, String suffix) {
        c.addContainerProperty("name" + suffix, String.class, null);
        c.addContainerProperty("company" + suffix, String.class, null);
        c.addContainerProperty("messages" + suffix, Integer.class, null);

        for (int i = 0; i < 100; i++) {
            Object itemId = c.addItem();
            c.getContainerProperty(itemId, "name" + suffix).setValue(
                    "Name " + i);
            c.getContainerProperty(itemId, "company" + suffix).setValue(
                    "Company name " + i);
            c.getContainerProperty(itemId, "messages" + suffix).setValue(i);
        }
    }

    private Button createShowDefaultHeaderContentButton() {
        Button b = new Button("Show default header content");
        b.addClickListener(new ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                removeAllScrollContents();
            }
        });
        return b;
    }

    private Button createShowSimpleHeaderContentButton() {
        Button b = new Button("Show simple header content");
        b.addClickListener(new ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                removeAllScrollContents();
                table.addScrollContent(ContentFactory
                        .createSimpleHeaderContent());
            }
        });
        return b;
    }

    private Button createShowAdvancedHeaderContentButton() {
        Button b = new Button("Show advanced header content");
        b.addClickListener(new ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                removeAllScrollContents();
                table.addScrollContent(ContentFactory
                        .createComplexHeaderContent());
            }
        });
        return b;
    }

    private void removeAllScrollContents() {
        for (ScrollContent sc : table.getScrollContents()) {
            table.removeScrollContent(sc);
        }
    }

}
