package com.ks.hrms.core.app;


import de.jensd.fx.glyphs.GlyphIcons;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;

public enum ButtonType {

    EXIT(FontAwesomeIcon.SIGN_OUT, SUCCESS()), NEW(FontAwesomeIcon.HAND_ALT_UP, PRIMARY()), VIEW(FontAwesomeIcon.NEWSPAPER_ALT, SUCCESS()), MODIFY(FontAwesomeIcon.EDIT, PRIMARY()), UPDATE(FontAwesomeIcon.REFRESH, PRIMARY()), DELETE(FontAwesomeIcon.TRASH, DANGER()), UNDO(FontAwesomeIcon.REPLY, DANGER()), CLEAR(FontAwesomeIcon.HASHTAG, WARING()), SEARCH(FontAwesomeIcon.SEARCH, SPECIAL()), SEARCH_PARAMETER(FontAwesomeIcon.FILE_TEXT, SPECIAL());

    private String style;
    private GlyphIcons icons;

    ButtonType(GlyphIcons icons, String color) {
        style = String.format("-fx-base:%s;", color);
        this.icons = icons;
    }

    static String SUCCESS() {
        return "#38B03F";
    }

    static String PRIMARY() {
        return "#3280FC";
    }

    static String WARING() {
        return "#F1A325";
    }

    static String DANGER() {
        return "#EA644A";
    }

    static String SPECIAL() {
        return "#8666B8";
    }

    public String getStyle() {
        return style;
    }

    public GlyphIcons getIcons() {
        return icons;
    }
}
