package com.ks.hrms.core.app;

import de.jensd.fx.glyphs.GlyphIcons;
import de.jensd.fx.glyphs.GlyphsDude;
import javafx.scene.control.Button;

public class DefaultToolBar extends AbstractToolbar {

    @Override
    Button generateButton(ButtonType type) {
        return ToolbarAppButtonFactory.generateButton(type);
    }

    static class ToolbarAppButtonFactory {

        public static Button generateButton(ButtonType type) {

            switch (type) {
                case EXIT:
                    return newInstance("退出", type.getIcons(), type.getStyle());
                case NEW:
                    return newInstance("新增", type.getIcons(), type.getStyle());
                case VIEW:
                    return newInstance("查阅", type.getIcons(), type.getStyle());
                case MODIFY:
                    return newInstance("修改", type.getIcons(), type.getStyle());
                case UPDATE:
                    return newInstance("更新", type.getIcons(), type.getStyle());
                case DELETE:
                    return newInstance("删除", type.getIcons(), type.getStyle());
                case UNDO:
                    return newInstance("还原", type.getIcons(), type.getStyle());
                case CLEAR:
                    return newInstance("清除", type.getIcons(), type.getStyle());
                case SEARCH:
                    return newInstance("搜索", type.getIcons(), type.getStyle());
                case SEARCH_PARAMETER:
                    return newInstance("搜索条件", type.getIcons(), type.getStyle());

            }

            return null;
        }

        static Button newInstance(String text, GlyphIcons icons, String style) {
            Button result = GlyphsDude.createIconButton(icons, text);
            result.setStyle(style);
            return result;
        }

    }
}
