package com.extjs.gxt.ui.client.widget.grid;

import java.util.HashMap;
import java.util.Map;

import com.extjs.gxt.ui.client.core.DomQuery;
import com.extjs.gxt.ui.client.core.El;
import com.extjs.gxt.ui.client.data.BaseModel;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.GridEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.ComponentHelper;
import com.extjs.gxt.ui.client.widget.ComponentPlugin;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Widget;

/**
 * This is adapted from {@link RowExpander}
 * adding the ability to set a {@link WidgetRowRenderer}
 * so that a widget can be embedded into the expanded
 * row body
 *
 * Typical usage:
 * <code>
 * WidgetExpander expander = new WidgetExpander(new WidgetRowRenderer() {
 *   public Widget render(BaseModel model, int rowIdx) {
 *     // return a widget according to the model
 *   }
 * });
 *
 * List<ColumnConfig> cfgs = new ArrayList<ColumnConfig>();
 * // setting up other columns
 * cfgs.add(expander);
 *
 * Grid grid = new Grid(store, new ColumnModel(cfgs));
 * grid.addGridPlugin(expander);
 *
 * </code>
 *
 * @author Kevin Qiu
 *
 * @param <T>
 */
public class WidgetExpander<T extends BaseModel> extends ColumnConfig implements ComponentPlugin {

    /**
     * Keep a reference to the grid the plugin is with.
     */
    private Grid<T> _grid;

    private WidgetRowRenderer<T> _renderer;

    private final Map<ModelData, Widget> _cachedWidgets = new HashMap<ModelData, Widget>();

    /**
     * Constructor.
     *
     * @param renderer
     */
    public WidgetExpander(final WidgetRowRenderer<T> renderer) {
        setHeader("");
        setWidth(20);
        setSortable(false);
        setResizable(false);
        setFixed(true);
        setMenuDisabled(true);
        setDataIndex("");
        setId("expander");

        setRenderer(new GridCellRenderer<ModelData>() {
            public String render(final ModelData model, final String property, final ColumnData d, final int rowIndex,
                            final int colIndex, final ListStore<ModelData> store, final Grid<ModelData> grid) {
                d.cellAttr = "rowspan='2'";
                return "<div class='x-grid3-row-expander'>&#160;</div>";
            }
        });

        setWidgetRowRenderer(renderer);
    }

    /**
     * Default constructor. This defers the setting of RowWidgetExpander till later.
     */
    public WidgetExpander() {
        this(null);
    }

    /**
     * Set the {@link RowWidgetRenderer}
     * @param renderer
     */
    public void setWidgetRowRenderer(final WidgetRowRenderer<T> renderer) {
        _renderer = renderer;
    }

    public WidgetRowRenderer<T> getWidgetRowRenderer() {
        return _renderer;
    }

    @SuppressWarnings("unchecked")
    public void init(final Component component) {
        this._grid = (Grid<T>) component;

        final GridView view = _grid.getView();

        final GridViewConfig config = view.getViewConfig();
        view.viewConfig = new GridViewConfig() {
            @Override
            public String getRowStyle(final ModelData model, final int rowIndex, final ListStore ds) {
                final String s = "x-grid3-row-collapsed";
                if (config != null) {
                    return s + " " + config.getRowStyle(model, rowIndex, ds);
                } else {
                    return s;
                }
            }
        };

        view.enableRowBody = true;

        _grid.addListener(Events.RowClick, new Listener<GridEvent<?>>() {
            public void handleEvent(final GridEvent<?> be) {
                onMouseDown(be);
            }

        });

    }


    protected final boolean beforeExpand(final ModelData model, final Element body, final El row, final int rowIndex) {
        return fireEvent(Events.BeforeExpand);
    }

    protected final void collapseRow(final El row) {
        if (fireEvent(Events.BeforeCollapse)) {
            row.replaceStyleName("x-grid3-row-expanded", "x-grid3-row-collapsed");
            fireEvent(Events.Collapse);
        }
    }


    @SuppressWarnings("unchecked")
    protected final void expandRow(final El row) {

        final int idx = row.dom.getPropertyInt("rowIndex");
        final ModelData model = _grid.getStore().getAt(idx);
        final Element body = DomQuery.selectNode("div.x-grid3-row-body", row.dom);
        body.setInnerText("");  // otherwise, there's "${body}" text in the expanded cell

        if (beforeExpand(model, body, row, idx)) {
            if (!_cachedWidgets.containsKey(model)) {
                assert _renderer != null;
                _cachedWidgets.put(model, _renderer.render((T)model, idx));
            }

            _grid.getView().fly(body).removeChildren();
            final Widget w = _cachedWidgets.get(model);
            body.appendChild(w.getElement());
            ComponentHelper.doAttach(w);

            row.replaceStyleName("x-grid3-row-collapsed", "x-grid3-row-expanded");
            fireEvent(Events.Expand);
        }
    }


    protected final void onMouseDown(final GridEvent<?> e) {
        if (e.getTarget().getClassName().equals("x-grid3-row-expander")) {
            e.stopEvent();
            final El row = e.getTarget(".x-grid3-row", 15);
            toggleRow(row);
        }
    }

    protected final void toggleRow(final El row) {
        if (row.hasStyleName("x-grid3-row-collapsed")) {
            expandRow(row);
        } else {
            collapseRow(row);
        }
        _grid.getView().calculateVBar(false);
    }

}
