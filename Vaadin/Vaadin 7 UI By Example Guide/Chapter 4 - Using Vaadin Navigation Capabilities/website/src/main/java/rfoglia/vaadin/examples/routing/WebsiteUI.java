package rfoglia.vaadin.examples.routing;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * This UI is the application entry point. A UI may either represent a browser window
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
@Widgetset("rfoglia.vaadin.examples.routing.MyAppWidgetset")
public class WebsiteUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {

        // website dir

        final VerticalLayout layout = new VerticalLayout();
        layout.setMargin(true);
        setContent(layout);
        String page = vaadinRequest.getPathInfo();
        if (page == null || page.isEmpty() || "/".equals(page)) {
            layout.addComponent(new Label("Welcome to Simple Web Site"));
            getPage().setTitle("Simple Web Site");
        } else if ("/page1".equals(page)) {
            layout.addComponent(
                    new Label("Oh yeah! You are watching Page 1!"));
            getPage().setTitle("Simple Web Site – Page 1");
        } else if ("/page2".equals(page)) {
            layout.addComponent(new Label("Yay! This is Page 2!"));
            getPage().setTitle("Simple Web Site – Page 2");
        } else {
            layout.addComponent(new Label(
                    "You just got 404'd! The requested page doesn't exist."));
            getPage().setTitle("Simple Web Site – 404!");
        }

    }

    @WebServlet(urlPatterns = "/*", name = "WebsiteUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = WebsiteUI.class, productionMode = false)
    public static class WebsiteUIServlet extends VaadinServlet {
    }
}
