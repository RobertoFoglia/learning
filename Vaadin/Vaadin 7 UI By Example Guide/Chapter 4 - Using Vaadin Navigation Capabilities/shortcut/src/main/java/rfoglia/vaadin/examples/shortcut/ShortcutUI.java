package rfoglia.vaadin.examples.shortcut;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.event.Action;
import com.vaadin.event.ShortcutAction;
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

// @@@ shortcut
@Theme("mytheme")
@Widgetset("rfoglia.vaadin.examples.shortcut.MyAppWidgetset")
public class ShortcutUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout layout = new VerticalLayout();
        layout.setMargin(true);
        layout.setSpacing(true);

        // @@@ panel example
        // @@@ shortcut on Windows and on Panel
        Panel panel = new Panel();
        panel.setSizeFull();
        panel.setContent(layout);
        setContent(panel);


        /** ##################################
         *   SHORTCUT FOR PANEL AND WINDOW
         * #################################### */
        panel.addActionHandler(new Action.Handler() {
            @Override
            public Action[] getActions(Object o, Object o1) {
                return new Action[]{
                        new ShortcutAction("Enter", ShortcutAction.KeyCode.ENTER, null),
                        new ShortcutAction("Shift + U", ShortcutAction.KeyCode.U,
                                new int[] { ShortcutAction.ModifierKey.SHIFT })
                };
            }

            @Override
            public void handleAction(Action action, Object o, Object o1) {
                Notification.show(action.getCaption());
            }
        });


        /** ##################################
        *   SHORTCUT FOR BUTTON
         * #################################### */

        final TextField tf = new TextField("Your data:");
        layout.addComponent(tf);

        Button button = new Button("Send data (ENTER)");

        // @@@ shortcut from button
        button.setClickShortcut(ShortcutAction.KeyCode.ENTER, ShortcutAction.ModifierKey.SHIFT);

        button.addClickListener(
                clickEvent -> {

                    layout.addComponent(new Label(tf.getValue()));
                    tf.setValue("");

                    // @@@ give to label the focus
                    // it only serves with the button click, no with shortcut
                    tf.focus();

                });

        layout.addComponent(button);


    }

    @WebServlet(urlPatterns = "/*", name = "ShortcutUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = ShortcutUI.class, productionMode = false)
    public static class ShortcutUIServlet extends VaadinServlet {
    }
}
