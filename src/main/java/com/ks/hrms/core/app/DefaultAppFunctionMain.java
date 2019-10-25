package com.ks.hrms.core.app;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.event.ActionEvent;

public class DefaultAppFunctionMain<T> extends AbstractAppFunctionMain<T> {

    private FunctionRequestListener requestListener;

    protected String createCustomButton(FontAwesomeIcon icon, String text) {
        return createCustomButton(icon, text, (String[]) null);
    }

    protected String createCustomButton(FontAwesomeIcon icon, String text, String popItems) {
        return createCustomButton(icon, text, new String[]{ popItems });
    }

    protected String createCustomButton(FontAwesomeIcon icon, String text, String[] popItems) {
        String charStr = icon.characterToString();
        String fontFamily = icon.getFontFamily();

        if (null != popItems) {
            text = String.format("%s:%s", text, Joiner.on(",").join(popItems));
        }
        return String.format("%s:%s\t%s", charStr, fontFamily, text);
    }

    @Override
    public ToolBar createToolbar() {
        ToolBar result = new DefaultToolBar();
        result.setToolbarTypes(getToolbarTypes());
        result.init();
        return result;
    }

    @Override
    public void close() {

    }

    @Override
    public void onClickButton(ButtonType type) {
        switch (type) {
            case UPDATE:
                this.update();
                break;
            case UNDO:
                break;
            case CLEAR:
                break;
            case DELETE:
                break;
            default:
                requestListener.request(this, type);
        }

    }

    @Override
    public void setFunctionRequestListener(FunctionRequestListener listener) {
        requestListener = listener;
    }

    @Override
    public ButtonType[] getToolbarTypes() {
        return new ButtonType[]{ ButtonType.EXIT, ButtonType.UPDATE, ButtonType.UNDO, ButtonType.CLEAR };
    }

    @Override
    public void newItem() {
        System.out.println("newItem");
    }

    @Override
    public void setItem(Item<T> item) {
        System.out.println("setItem");
    }

    @Override
    public boolean update() {
        System.out.println("update");
        return true;
    }
}
