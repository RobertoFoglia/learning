package rfoglia.vaadin.examples.progressindicator;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.UI;
import com.vaadin.ui.ProgressIndicator;
import com.vaadin.ui.VerticalLayout;

import javax.servlet.annotation.WebServlet;

// @@@ progress bar (deprecated)

/**
 * This UI is the application entry point. A UI may either represent a browser window
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
@Widgetset("rfoglia.vaadin.examples.progressindicator.MyAppWidgetset")
public class ProgressindicatorUI extends UI {

    private ProgressIndicator mrProgressIndicator = new ProgressIndicator();

    private class HighTechAlgorithm extends Thread {
        @Override
        public void run() {
            try {

                for (int i = 1; i <= 10; i++) {
                    sleep(1000); // it works in debug so you can show the progress only in debug
                    mrProgressIndicator.setValue(i * 0.1f);
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout layout = new VerticalLayout();
        layout.setMargin(true);
        setContent(layout);

        Button button = new Button("Start awesome algorithm");

        button.addClickListener(event ->
                new HighTechAlgorithm().start());

        layout.addComponent(button);
        layout.addComponent(mrProgressIndicator);
    }

    @WebServlet(urlPatterns = "/*", name = "ProgressindicatorUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = ProgressindicatorUI.class, productionMode = false)
    public static class ProgressindicatorUIServlet extends VaadinServlet {
    }
}
