package com.ks.hrms.core.app;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;

public class DefaultAppFunctionMain extends AbstractAppFunctionMain {

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

    public ButtonType[] getToolbarIds(){
        return new ButtonType[] {ButtonType.EXIT, ButtonType.UPDATE, ButtonType.UNDO, ButtonType.CLEAR};
    }

}
