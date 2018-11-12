package org.test;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of an HTML page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout layout = new VerticalLayout();

        // @@@ SET MARGIN
        layout.setMargin(true);

        final TextField name = new TextField();
        name.setCaption("Type your name here:");


        final TextField name1 = new TextField("Somebody's name");
        final TextField name2 = new TextField("somebody's name");
        layout.addComponent(name1);
        layout.addComponent(name2);

        // @@@ FIRST EXAMPLE WITH MAIN COMPONENTS PAG 21
        Button button = new Button("Click Me");
        button.addClickListener(e -> {
            {
                Label label = new Label("<b>Thanks</b> " + name.getValue()
                        + ", it works!");
                label.setContentMode(ContentMode.HTML); // set the content as HTML text
                layout.addComponent(label);
                String phrase = getFunnyPhrase(
                        name1.getValue(), name2.getValue());
                layout.addComponent(new Label(phrase));
                Notification.show("@@@ Fancy notification pag 25");
            }
        });
        
        layout.addComponents(name, button);
        
        setContent(layout);
    }

    public String getFunnyPhrase(String name1, String name2) {
        String[] verbs = new String[] {
                "eats", "melts", "breaks", "washes", "sells"};
        String[] bodyParts = new String[] {
                "head", "hands", "waist", "eyes", "elbows"};
        return name1 + " " +
                verbs[(int) (Math.random() * 100 % verbs.length)] + " " +
                name2 + "'s " +
                bodyParts[(int) (Math.random() * 100 % verbs.length)];
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
