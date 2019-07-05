package com.ks.hrms.core.app;


import javafx.scene.Node;
import javafx.scene.layout.Region;

public class AppFunctionPageBrowser extends Region {

    private AppPage currentPage;

    public AppFunctionPageBrowser(AppPage currentPage) {
        this.currentPage = currentPage;
        getChildren().add((Node) currentPage);
    }

    public void forward(){
        if(currentPage.hasNext()){
            getChildren().remove((Node) currentPage);
            AppPage lastPage = currentPage.getNext();
            currentPage = lastPage;
            getChildren().add((Node) currentPage);
        }
    }

    /**
     * 返回到上一个Page 检查当前page有无上级
     *
     */
    public void back(){
        if(currentPage.hasLast()){
            getChildren().remove((Node) currentPage);
            AppPage lastPage = currentPage.getLast();
            currentPage = lastPage;
            getChildren().add((Node) currentPage);
        }

    }

    @Override
    protected void layoutChildren() {
        if (currentPage != null) {
            ((Node) currentPage).resize(getWidth(), getHeight());
        }
    }

}
