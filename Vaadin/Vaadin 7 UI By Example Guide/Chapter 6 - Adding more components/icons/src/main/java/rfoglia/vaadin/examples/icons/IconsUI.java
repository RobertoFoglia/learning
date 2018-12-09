package rfoglia.vaadin.examples.icons;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.ClassResource;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
@Widgetset("rfoglia.vaadin.examples.icons.MyAppWidgetset")
public class IconsUI extends UI {

    private TextField tf = new TextField("Email");
    private ComboBox cb = new ComboBox("Type");
    private TextArea ta = new TextArea("Details");
    private OptionGroup og = new OptionGroup("Priority");
    private Button bt = new Button("Send");

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout layout = new VerticalLayout();
        layout.setMargin(true);
        layout.setSpacing(true);
        setContent(layout);

        tf.setWidth("100%");
        cb.setWidth("100%");
        ta.setWidth("100%");

        og.setWidth("100%");
        og.addItem("Too low");
        og.addItem("Extremely high");

        HorizontalLayout top = new HorizontalLayout();
        top.setWidth("100%");
        top.setSpacing(true);

        HorizontalLayout bottom = new HorizontalLayout();
        bottom.setWidth("100%");
        bottom.setSpacing(true);

        top.addComponent(tf);
        top.addComponent(cb);
        bottom.addComponent(og);
        bottom.addComponent(bt);

        layout.addComponent(top);
        layout.addComponent(ta);
        layout.addComponent(bottom);

        bottom.setComponentAlignment(bt, Alignment.BOTTOM_RIGHT);

        tf.setIcon(new ThemeResource("images/email.png"));
        cb.setIcon(new ThemeResource("images/note.png"));
        ta.setIcon(new ThemeResource("images/document.png"));
        bt.setIcon(new ThemeResource("images/ok.png"));

        og.setItemIcon("Too low", new ThemeResource("images/attention.png"));
        og.setItemIcon("Extremely high", new ThemeResource("images/error.png"));
    }

    @WebServlet(urlPatterns = "/*", name = "IconsUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = IconsUI.class, productionMode = false)
    public static class IconsUIServlet extends VaadinServlet {
    }
}
