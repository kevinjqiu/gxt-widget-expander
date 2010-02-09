package com.extjs.gxt.ui.client.widget.grid;

import com.extjs.gxt.ui.client.data.BaseModel;
import com.google.gwt.user.client.ui.Widget;

/**
 * This is a delegate that returns a widget according to
 * the {@BaseModel} object to be rendered
 *
 * @author Kevin Qiu
 *
 * @param <T>
 */
public interface WidgetRowRenderer<T extends BaseModel> {


    /**
     * Renders the row into a GWT widget
     *
     * @param model The model of the row to be rendered
     * @param rowIdx The index of the row
     * @return a GWT {@link Widget} representing the row
     */
    Widget render(T model, int rowIdx);

}
