package binding;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @@@ DATA BINDING
 */
@Theme("mytheme")
@Widgetset("binding.MyAppWidgetset")
public class BindingUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout layout = new VerticalLayout();

        List<Component> addedComponent = new ArrayList<>();

        TextField textField = new TextField("Data binding");
        textField.setImmediate(true);
        Label label = new Label();

        /**
         * BINDING - DATASOURCE
         **/

        ObjectProperty<String> property =
                new ObjectProperty<>("the value");
        // ATTACH THE DATASOURCE TO VIEW
        textField.setPropertyDataSource(property);
        label.setPropertyDataSource(property);
        // the UI is updated when you leave the focus on text field
        addedComponent.add(textField);
        addedComponent.add(label);
        /***************************/

        /**
         * TEXT AREA
         **/
        // our TextArea component

        TextArea textArea = new TextArea(
                "Enter some multi-lined text and press TAB:");

        // data binding
        textArea.setPropertyDataSource(property);

        textArea.addValueChangeListener(
                valueChangeEvent ->
                {
                    String userText = valueChangeEvent.getProperty().getValue()
                            .toString();
                    Notification.show("This is it: " + userText);
                }
        );
        textArea.setImmediate(true);
        addedComponent.add(textArea);
        //***************************


        /**
         RICHTEXTAREA
         */

        RichTextArea richText = new RichTextArea("Rich text area:");
        richText.setImmediate(true);
        Label rTAlabel = new Label(richText, ContentMode.HTML);
        addedComponent.add(richText);
        addedComponent.add(rTAlabel);

        /**************************************/

        /**
         *      OPTIONGROUP
         */

        // an array with the options
        ArrayList<String> answers = new ArrayList<>();
        answers.add("<b>Vaadin beans</b>");
        answers.add("Session beans");
        answers.add("<b>Enterprise App for Vaadin beans</b>");
        answers.add("Message-driven beans");
        // our OptionGroup component
        OptionGroup og = new OptionGroup(
                "Two kinds of EJBs in Java EE are:", answers);
        og.addValueChangeListener(
                event ->
                    Notification.show(event.getProperty().getValue().toString())
                );
        og.setImmediate(true);
        addedComponent.add(og);

        OptionGroup og2 = new OptionGroup(
                "With setHtmlContentAllowed = true", answers);
        og2.setHtmlContentAllowed(true);
        addedComponent.add(og2);

        /**********************************************/

        initLayout(layout, addedComponent);
    }

    private void initLayout(AbstractOrderedLayout layout, List<Component> componentsToAdd) {
        // insert layout margin (left and right)
        layout.setMargin(true);
        // insert layout spacing among elements
        layout.setSpacing(true);

        componentsToAdd.forEach(layout::addComponent);

        setContent(layout);
    }

    @WebServlet(urlPatterns = "/*", name = "BindingUIServlet" +
            "" +
            "", asyncSupported = true)
    @VaadinServletConfiguration(ui = BindingUI.class, productionMode = false)
    public static class BindingUIServlet extends VaadinServlet {
    }
}
