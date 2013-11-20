package org.vaadin.tltv.multiscrolltable.demo;

import org.vaadin.tltv.multiscrolltable.ui.Column;
import org.vaadin.tltv.multiscrolltable.ui.ColumnGroup;
import org.vaadin.tltv.multiscrolltable.ui.HierarchicalColumnGroup;
import org.vaadin.tltv.multiscrolltable.ui.ScrollContent;

public class ContentFactory {

    public static ScrollContent createSimpleHeaderContent() {
        return createSimpleHeaderContent("");
    }

    public static ScrollContent createSimpleHeaderContent(String suffix) {
        ScrollContent sc = new ScrollContent();
        ColumnGroup cg = new ColumnGroup();
        cg.addColumn(new Column("name" + suffix));
        cg.addColumn(new Column("company" + suffix));
        cg.addColumn(new Column("messages" + suffix));
        sc.addColumnGroup(cg);
        return sc;
    }

    public static ScrollContent createComplexHeaderContent() {
        ScrollContent sc = new ScrollContent();

        HierarchicalColumnGroup cg1 = new HierarchicalColumnGroup();
        cg1.setCaption("Column group caption 1");
        ColumnGroup cg2 = new ColumnGroup();
        cg2.setCaption("Column group caption 2");
        ColumnGroup cg3 = new ColumnGroup();
        cg3.setCaption("Column group caption 3");

        cg1.addColumnGroup(cg2);
        cg1.addColumnGroup(cg3);
        cg2.addColumn(new Column("name"));
        cg3.addColumn(new Column("company"));
        cg3.addColumn(new Column("messages"));

        sc.addColumnGroup(cg1);
        return sc;
    }
}
