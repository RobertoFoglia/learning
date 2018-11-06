package timeit.ui;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.UserError;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;
import timeit.biz.TestSet;
import timeit.biz.TestSetExecutor;
import timeit.biz.test.LongVsInt;
import timeit.biz.test.ShortCircuitVsNoShortCircuit;
import timeit.biz.test.StringVsStringBuffer;

import java.util.Collection;

/**
 * // @@@ businees classes separations
 */
@Theme("mytheme")
@Widgetset("timeit.ui.MyAppWidgetset")
public class TimeItUi extends UI {

    private static final TestSet[] testSets = new TestSet[]{
            new LongVsInt(),
            new StringVsStringBuffer(),
            new ShortCircuitVsNoShortCircuit()
    };
    private VerticalLayout layout = new VerticalLayout();
    private ComboBox combo = new ComboBox("Test");
    private final TextField textField = new TextField("Number of iterations", "1000");
    private CheckBox checkBox = new CheckBox("Keep previous result");
    private Button button = new Button("Time it!");
    private VerticalLayout resultsLayout = new VerticalLayout();

    @Override
    protected void init(VaadinRequest vaadinRequest) {

        initCombo();
        initButton();
        initLayout();

        setContent(layout);
    }

    private void initCombo() {
        for (TestSet testSet : testSets) {
            combo.addItem(testSet);
            combo.setItemCaption(testSet, testSet.getTitle());
        }
        combo.addValueChangeListener(event -> {
            TestSet testSet = (TestSet) combo.getValue();
            textField.setValue("" + testSet.getDefaultTimes());
            button.setDescription(testSet.getDescription());
        });
        combo.setImmediate(true);

        layout.addComponent(combo);
    }

    private void initButton() {
        button.addClickListener(clickEvent -> {
            // @@@ input validation examples
            if (isValid()) {
                runSelectedTest();
            }
        });
    }

    public boolean isValid() {
        combo.setComponentError(null);
        textField.setComponentError(null);
        boolean isValid = true;

        if (combo.getValue() == null) {
            combo.setComponentError(
                    new UserError("Select a test from the list."));
            isValid = false;
        }
        if (textField.getValue().isEmpty() || !textField.getValue().matches("-?\\d+(\\.\\d+)?")) {
            textField.setComponentError(new UserError(
                    "You must introduce the number of iterations to execute"));
            isValid = false;
        }

        return isValid;
    }

    private void initLayout() {
        layout.setMargin(true);
        layout.setSpacing(true);
        layout.addComponent(combo);
        layout.addComponent(textField);
        layout.addComponent(checkBox);
        layout.addComponent(button);
        layout.addComponent(resultsLayout);
        setContent(layout);
    }

    public boolean validate() {
        return true;
    }

    public void runSelectedTest() {
        Long times = Long.parseLong(textField.getValue());
        Collection<String> results = TestSetExecutor.execute(
                (TestSet) combo.getValue(), times);
        showResults(results);
    }

    private void showResults(Collection<String> results) {
        if (!checkBox.getValue()) {
            resultsLayout.removeAllComponents();
        } else if (resultsLayout.getComponentCount() > 0) {
            resultsLayout.addComponent(new Label("--"));
        }
        for (String result : results) {
            resultsLayout.addComponent(new Label(result));
        }
    }


    @WebServlet(urlPatterns = "/*", name = "TimeItUiServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = TimeItUi.class, productionMode = false)
    public static class TimeItUiServlet extends VaadinServlet {
    }
}
