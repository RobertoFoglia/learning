package rfoglia.vaadin.examples.table;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;

import javax.servlet.annotation.WebServlet;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
// @@@ First table with header and footer
@Theme("mytheme")
@Widgetset("rfoglia.vaadin.examples.table.MyAppWidgetset")
public class MyFirstTableUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout layout = new VerticalLayout();
        layout.setMargin(true);
        layout.setSpacing(true);
        setContent(layout);

        final Table table = new Table();

        // add column or header
        table.addContainerProperty("Column 1", String.class, "(default 1)");
        table.addContainerProperty("Column 2", String.class, "(default 2)");
        table.addContainerProperty("Column 3", String.class, "(default 3)");
        table.addHeaderClickListener(
                headerClickEvent ->
                        Notification.show("Header clicked: " + headerClickEvent.getPropertyId())
        );

        // add item
        table.addItem("item ID 0"); // default values
        table.addItem(new Object[] { "Hi", "There" , "bye bye"}, "item ID 1");
        table.addItem(new Object[] { "Well", "Hello!" }, "item ID 2"); // it is not inserted
        table.addItem(new Object[] { "Well 3", "Hello! 3", "Hello and Hi 3" }, "item ID 2");
        table.addItem(); // default values

        Item item2 = table.getItem("item ID 2");
                Button insertData = new Button("modify 3rd row");
        insertData.addClickListener(
                event -> {
                    Property column1 = item2.getItemProperty("Column 1");
                    Property column2 = item2.getItemProperty("Column 2");
                    Property column3 = item2.getItemProperty("Column 3");
                    // set the value for each property (or column)
                    if("Well 3".equals(column1.getValue())) {
                        column1.setValue("Roberto");
                        column2.setValue("Foglia");
                        column3.setValue("Roberto");
                    } else {

                        if ("Roberto".equals(column1.getValue())) {
                            column1.setValue("Well 3");
                            column2.setValue("Hello");
                            column3.setValue("Changes");
                        }

                    }
                 }
        );

        // set footer
        table.setColumnFooter("Column 1", "Footer 1");
        table.setColumnFooter("Column 2", "Footer2");
        table.setFooterVisible(true);

        table.addFooterClickListener(
                footerClickEvent ->
                        Notification.show("Footer clicked: " + footerClickEvent.getPropertyId())
        );

        layout.addComponent(table);
        layout.addComponent(insertData);
    }

    @WebServlet(urlPatterns = "/*", name = "MyFirstTableUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyFirstTableUI.class, productionMode = false)
    public static class MyFirstTableUIServlet extends VaadinServlet {
    }
}
