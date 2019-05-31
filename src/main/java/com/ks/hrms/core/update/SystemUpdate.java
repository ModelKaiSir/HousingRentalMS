package com.ks.hrms.core.update;

/**
 * 通过spring自动扫描所有更新类 进行更新
 * @author QiuKai
 */
public interface SystemUpdate {

    String getVer();

    void update();

    String getDateTimeStr();
}
