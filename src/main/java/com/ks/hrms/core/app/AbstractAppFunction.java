package com.ks.hrms.core.app;

import com.ks.hrms.core.component.CustomStyleClass;
import com.ks.hrms.core.context.AppFunctionContext;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public abstract class AbstractAppFunction<T> extends Tab implements Function<T>, FunctionComponent {

    public static final String STYLESHEET = "UI/functionUI.css";
    public static final String STYLE_FUNCTION = "function";

    protected AppFunctionContext context = AppFunctionContext.getInstance();

    private FunctionLayout layout;
    private FunctionMain main;
    private FunctionNavigator navigator;

    private Class[] setFunctions = {
            FunctionMain.class,
            FunctionNavigator.class
    };

    public AbstractAppFunction() {
    }

    @Override
    public void init() {

        getStyleClass().add(STYLE_FUNCTION);
        layout = createFunctionLayout();
        navigator = createFunctionNavigator();
        main = createFunctionMain();

        initComponent(main);
        initComponent(navigator);

        if (null != navigator) {
            layout.setFocusedFunctionComponent(navigator);
        } else if (null != main) {
            layout.setFocusedFunctionComponent(main);
        }

        initComponent(layout);
        setContent((Parent) layout);
        //
        fitToParent((Parent) layout);

        setOnClosed(event -> {
            onClose(null);
        });
    }

    abstract FunctionMain createFunctionMain();

    abstract FunctionNavigator createFunctionNavigator();

    abstract FunctionLayout createFunctionLayout();

    protected void fitToParent(Parent parent) {
        AnchorPane.setTopAnchor(parent, 0.0);
        AnchorPane.setBottomAnchor(parent, 0.0);
        AnchorPane.setLeftAnchor(parent, 0.0);
        AnchorPane.setRightAnchor(parent, 0.0);
    }

    private void initComponent(FunctionComponent component) {
        if (null != component) {
            component.init();

            Class clazz = component.getClass();

            try {
                for (Class c : setFunctions) {
                    if (clazz.isAssignableFrom(c)) {
                        Method method = component.getClass().getMethod("setFunction", Function.class);
                        method.invoke(component, this);
                        break;
                    }
                }

            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public FunctionMain getFunctionMain() {
        return main;
    }

    @Override
    public FunctionNavigator getNavigator() {
        return navigator;
    }

    public FunctionLayout getLayout() {
        return layout;
    }

    public void setCaption(String caption) {
        setText(caption);
    }

    @Override
    public void close() {
        onClose(null);
        getTabPane().getTabs().remove(this);
    }
}
