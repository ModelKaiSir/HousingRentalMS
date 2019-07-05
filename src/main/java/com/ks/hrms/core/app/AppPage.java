package com.ks.hrms.core.app;

public interface AppPage {

    AppPage getNext();

    AppPage getLast();

    void setNext(AppPage page);

    void setLast(AppPage page);

    boolean hasNext();

    boolean hasLast();

    int getIndex();

    void setPageIndex(int pageIndex);
}
