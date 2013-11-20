package org.vaadin.tltv.multiscrolltable.demo;

import java.util.ArrayList;
import java.util.List;

import org.vaadin.tltv.multiscrolltable.ui.CustomScrollTable;
import org.vaadin.tltv.multiscrolltable.ui.ScrollContent;

import com.vaadin.data.Container;
import com.vaadin.data.Container.Hierarchical;
import com.vaadin.data.util.HierarchicalContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class MultiScrollContentsDemo extends VerticalLayout {

    private final CustomScrollTable table;

    private int suffix = 0;

    public MultiScrollContentsDemo() {
        table = new CustomScrollTable(prepareContainer());
        table.setHeight(500, Unit.PIXELS);
        table.setRowHeaderPropertyId("name");
        addComponent(table);

        // override the default scroll content
        table.addScrollContent(ContentFactory.createSimpleHeaderContent(""));

        GridLayout buttons = new GridLayout(3, 1);
        buttons.addComponent(createAddScrollContentButton());
        buttons.addComponent(createRemoveScrollContentButton());
        addComponent(buttons);
    }

    private Component createRemoveScrollContentButton() {
        Button b = new Button("Remove last scroll content");
        b.addClickListener(new ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                List<ScrollContent> scrollcontents = new ArrayList<ScrollContent>(
                        table.getScrollContents());
                if (scrollcontents.size() == 0) {
                    return;
                }
                table.removeScrollContent(scrollcontents.get(scrollcontents
                        .size() - 1));
                removeColumns(table.getContainerDataSource(), "" + suffix--);
                table.setVisibleColumns(table.getContainerDataSource()
                        .getContainerPropertyIds());
            }
        });
        return b;
    }

    private Component createAddScrollContentButton() {
        Button b = new Button("Add new scroll content");
        b.addClickListener(new ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                String key = "" + (++suffix);
                addColumnsForNewContent(table.getContainerDataSource(), key);
                table.addScrollContent(ContentFactory
                        .createSimpleHeaderContent(key));
            }
        });
        return b;
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
            Object itemId = i;
            if (suffix == null || suffix.trim().isEmpty()) {
                c.addItem(itemId);
            }
            c.getContainerProperty(itemId, "name" + suffix).setValue(
                    "Name " + i);
            c.getContainerProperty(itemId, "company" + suffix).setValue(
                    "Company name " + i);
            c.getContainerProperty(itemId, "messages" + suffix).setValue(i);
        }
    }

    private void removeColumns(Hierarchical c, String suffix) {
        c.removeContainerProperty("name" + suffix);
        c.removeContainerProperty("company" + suffix);
        c.removeContainerProperty("messages" + suffix);
    }
}
