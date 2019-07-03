package com.ks.hrms.core.component.ui.extend;

/**
 * 获得可以隐藏标题的能力
 * @author QiuKai
 */
public interface CaptionAble<T> {

    /**
     * 是否隐藏标题
     * @param b
     * @return
     */
    T hideCaption(boolean b);

}
