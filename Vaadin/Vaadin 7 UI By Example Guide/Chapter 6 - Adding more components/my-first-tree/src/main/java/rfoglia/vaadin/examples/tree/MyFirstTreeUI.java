package rfoglia.vaadin.examples.tree;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.data.util.HierarchicalContainer;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
// @@@ TREE
@Theme("mytheme")
@Widgetset("rfoglia.vaadin.examples.tree.MyAppWidgetset")
public class MyFirstTreeUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout layout = new VerticalLayout();
        layout.setMargin(true);
        setContent(layout);

        Tree familyTree = new Tree();

        familyTree.addItem("Families");
        familyTree.addItem("The Jackson");
        familyTree.addItem("The Simpsons");
        familyTree.addItem("The Rothschilds");
        familyTree.addItem("The Hapsburgs");
        familyTree.addItem("The Addams");

        familyTree.setParent("The Jackson", "Families");
        familyTree.setParent("The Simpsons", "Families");
        familyTree.setParent("The Rothschilds", "Families");
        familyTree.setParent("The Hapsburgs", "Families");
        familyTree.setParent("The Addams", "Families");

        familyTree.setChildrenAllowed("The Jackson", false);
        familyTree.setChildrenAllowed("The Simpsons", false);
        familyTree.setChildrenAllowed("The Rothschilds", false);
        familyTree.setChildrenAllowed("The Hapsburgs", false);
        familyTree.setChildrenAllowed("The Addams", false);

        familyTree.addItemClickListener(
                event -> {
                    Notification.show(event.getItemId() + " clicked.");
                });

        familyTree.addExpandListener(
                event -> {
                    Notification.show(event.getItemId() + " expanded.");
                });

        familyTree.addCollapseListener(
                event -> {
                    Notification.show(event.getItemId() + " collapsed.");
                });


        Tree familyTree2 = new Tree();
        HierarchicalContainer container =
                new HierarchicalContainer();
        container.addItem("Dad");
        container.addItem("Sonny");
        container.setParent("Sonny", "Dad");
        familyTree2.setContainerDataSource(container);

        layout.addComponent(familyTree);
        layout.addComponent(familyTree2);
    }

    @WebServlet(urlPatterns = "/*", name = "MyFirstTreeUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyFirstTreeUI.class, productionMode = false)
    public static class MyFirstTreeUIServlet extends VaadinServlet {
    }
}
