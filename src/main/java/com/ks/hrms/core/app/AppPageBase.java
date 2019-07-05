package com.ks.hrms.core.app;

import javafx.scene.layout.BorderPane;

public class AppPageBase extends BorderPane implements AppPage {

    private AppPage next, last;
    private int pageIndex;

    @Override
    public AppPage getNext() {
        return next;
    }

    @Override
    public AppPage getLast() {
        return last;
    }

    @Override
    public boolean hasNext() {
        return null != next;
    }

    @Override
    public boolean hasLast() {
        return null != last;
    }

    @Override
    public int getIndex() {
        return pageIndex;
    }

    @Override
    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    @Override
    public void setNext(AppPage page) {
        next = page;
    }

    @Override
    public void setLast(AppPage page) {
        last = page;
    }
}
