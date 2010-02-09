package com.github.gxtexpander.demo.client;

import java.util.ArrayList;
import java.util.List;

import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.WidgetExpander;
import com.extjs.gxt.ui.client.widget.grid.WidgetRowRenderer;
import com.github.gxtexpander.demo.client.models.DemoDataFactory;
import com.github.gxtexpander.demo.client.models.User;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Application implements EntryPoint {

    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {
        final List<ColumnConfig> cfg = new ArrayList<ColumnConfig>();

        final WidgetExpander<User> expander = new WidgetExpander<User>(new WidgetRowRenderer<User>() {

            public Widget render(final User model, final int rowIdx) {
                return new Label("" +  model.getLanguages());
            }
        });
        cfg.add(expander);

        final ColumnConfig cc = new ColumnConfig("name", "Name", 100);
        cfg.add(cc);


        final Grid<User> grid = new Grid<User>(DemoDataFactory.createDemoDataListStore(), new ColumnModel(cfg));
        grid.setHeight("500px");
        grid.setAutoExpandColumn("name");
        grid.addPlugin(expander);

        RootPanel.get().add(grid);
    }
}
