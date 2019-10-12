package com.ks.hrms.core.app;

import com.ks.hrms.core.component.CustomStyleClass;
import com.ks.hrms.core.context.AppFunctionContext;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public abstract class AbstractAppFunction extends Tab implements Function, FunctionComponent, FunctionComponent.FunctionRequestListener {

    public static final String STYLESHEET = "UI/functionUI.css";
    public static final String STYLE_FUNCTION = "function";

    protected AppFunctionContext context = AppFunctionContext.getInstance();

    private FunctionLayout layout;
    private FunctionToolbar toolbar;
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
        layout = createLayout();
        toolbar = createToolbar();
        main = createMain();
        navigator = createNavigator();
        initComponent(toolbar);
        initComponent(main);
        initComponent(navigator);

        if (null != toolbar) {
            layout.setFunctionToolbar(toolbar);
        }

        if (null != navigator) {
            layout.setFocusedFunctionComponent(navigator);
        } else if (null != main) {
            layout.setFocusedFunctionComponent(main);
        }

        initComponent(layout);
        setContent((BorderPane) layout);
        //
        AnchorPane.setTopAnchor((BorderPane) layout, 0.0);
        AnchorPane.setBottomAnchor((BorderPane) layout, 0.0);
        AnchorPane.setLeftAnchor((BorderPane) layout, 0.0);
        AnchorPane.setRightAnchor((BorderPane) layout, 0.0);
    }

    /**
     * ToolBar
     *
     * @return
     */
    abstract FunctionToolbar createToolbar();


    /**
     * ToolBar
     * @param type
     */
    @Override
    public void request(ButtonType type) {
        switch (type){
            case EXIT:
                // layout.getFocusedFunctionComponent()
                break;
        }
    }

    private void initComponent(FunctionComponent component) {
        if (null != component) {
            component.init();

            Class clazz = component.getClass();

            try {
                for (Class c : setFunctions) {
                    if(clazz.isAssignableFrom(c)){
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
    public FunctionMain getMain() {
        return main;
    }

    @Override
    public FunctionNavigator getNavigator() {
        return navigator;
    }

    @Override
    public void setCaption(String caption) {
        setText(caption);
        layout.setCaption(caption);
    }
}
