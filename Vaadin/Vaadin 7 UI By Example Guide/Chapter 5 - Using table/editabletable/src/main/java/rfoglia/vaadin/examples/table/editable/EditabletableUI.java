package rfoglia.vaadin.examples.table.editable;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Table;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import javax.servlet.annotation.WebServlet;
import java.util.Date;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
@Widgetset("rfoglia.vaadin.examples.table.editable.MyAppWidgetset")
public class EditabletableUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout layout = new VerticalLayout();
        layout.setMargin(true);
        layout.setSpacing(true);
        setContent(layout);

        Table table = new Table();
        table.setPageLength(0);
        table.setEditable(true);

        table.addContainerProperty("String", String.class, "");
        table.addContainerProperty("Boolean", Boolean.class, false);
        table.addContainerProperty("Date", Date.class, null);
        table.addContainerProperty("Item", Item.class, null);

        // if the id is not specified then the id will be numeric 1,2,3,4
        table.addItem();
        table.addItem();
        table.addItem();

        User user = new User();
        user.setLogin("mylogin");
        user.setPassword("mypassword");

        // @@@ table - data binding in view
        BeanItem<User> item = new BeanItem<>(user);
        table.addItem(new Object[] { "", true, new Date(), item }, 4);

        // the button doesn't work
        // TODO robertofoglia you must find the correct way to bind the User class with the view field
        Button button = new Button("change login and password");
        button.addClickListener(event -> {
            user.setLogin("roberto");
            user.setPassword("foglia");
        });

        layout.addComponent(table);
        layout.addComponent(button);
    }

    @WebServlet(urlPatterns = "/*", name = "EditabletableUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = EditabletableUI.class, productionMode = false)
    public static class EditabletableUIServlet extends VaadinServlet {
    }
}
