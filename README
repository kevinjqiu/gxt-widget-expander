# GXT Widget Expander #

## Motivation ##
GXT (Ext-GWT) is a widget library built on top of GWT.

GXT has a very feature-rich grid component, together with a row expander plugin that allows a row of the grid to be expanded to display extra information.
However, the current design of RowExpander is limited, as it can only render the selected row with XTemplate. This significantly limits the usability of the expander in the following ways:
- XTemplate is basically templated HTML, which means the code inside the expanded body is left on its own without the blessing of GWT's cross-browser support.
- The element inside the expanded body cannot respond to events without embedding Javascript in the XTemplate.

GXT Widget Expander is designed to tackle these problems and ultimately, allow richer user interaction inside the expanded row body.

## Usage ##
    WidgetExpander<SomeModel> expander = new WidgetExpander<SomeModel>(new WidgetRowRenderer<SomeModel>() {

        public Widget render(final SomeModel model, final int rowIdx) {
            // Create and return a widget according to the model
        }
    });

    // Add expander into a list of ColumnConfigs
    // Create a grid with the ColumnConfig list
    grid.addGridPlugin(expander);


