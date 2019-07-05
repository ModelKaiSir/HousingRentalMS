package com.ks.hrms.core.app;

import com.ks.hrms.core.context.HRMSAppFunctionContext;
import javafx.scene.control.Tab;

import java.util.ArrayList;

/**
 * AppFunction
 *
 * @author QiuKai
 */
public abstract class AppFunctionBase extends Tab {

    private int pageIndex = 0;
    private AppFunctionPageBrowser pageBrowser;
    private HRMSAppFunctionContext context;

    private AppFunctionNavigator navigator;
    private AppFunctionMain functionMain;

    private AppPage currentPage;
    private ArrayList<AppPage> pages;

    public AppFunctionBase() {
    }

    public void init(HRMSAppFunctionContext context) {

        pages = new ArrayList<>();
        navigator = createNavigator();
        registerApp(null, navigator);
        functionMain = createMain();
        registerApp(navigator, functionMain);
        pageBrowser = new AppFunctionPageBrowser(currentPage);
        setContent(pageBrowser);
    }

    /**
     * 主窗口
     * @return
     */
    public abstract AppFunctionMain createMain();

    /**
     * 导航窗口
     * @return
     */
    public abstract AppFunctionNavigator createNavigator();

    /**
     * 关闭Function 先检查是否存在上一节点 存在则切换上一节点 否则关闭界面
     * @param page
     */
    public void closePage(AppPage page) {
        if (page.hasLast()) {
            pageBrowser.back();
        } else {
            close();
        }
    }

    /**
     * 注册AppPage
     * @param cache
     * @param page
     */
    private void registerApp(AppPage cache, AppPage page) {
        if (null != page) {

            if (page instanceof App) {
                ((App) page).init(context);
            }

            page.setPageIndex(++pageIndex);
            if (null != cache) {
                page.setLast(cache);
                cache.setNext(page);
            }else{
                currentPage = page;
            }

            pages.add(page);
        }

    }

    private void close() {
        getTabPane().getTabs().remove(this);
    }
}
