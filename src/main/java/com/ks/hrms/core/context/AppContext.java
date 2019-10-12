package com.ks.hrms.core.context;

import com.ks.hrms.dao.SystemDao;
import javafx.beans.property.SimpleStringProperty;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;

public interface AppContext {

    /**
     * 获取程序版本
     *
     * @return
     */
    SimpleStringProperty getVer();

    /**
     * 检查版本
     *
     * @param oldVer 旧版本
     * @return
     */
    boolean checkVer(String oldVer);

    /**
     * 关于程序
     *
     * @return
     */
    SimpleStringProperty about();

    /**
     * 获取程序名称
     *
     * @return
     */
    SimpleStringProperty getAppName();

    /**
     * 更新程序信息
     *
     * @param infomation 程序信息
     */
    void updateSystemInfomation(SystemDao.SystemInfomation infomation);

    /**
     * 初始化
     */
    void init();

    /**
     * 版本更新
     */
    void update();

    /**
     *
     * @param applicationContext
     * @throws BeansException
     */
    void setApplicationContext(ApplicationContext applicationContext) throws BeansException;

    /**
     *
     * @param beanName
     * @param tClass
     * @param <T>
     * @return
     */
    <T> T getBean(String beanName,Class<T> tClass);

    /**
     *
     * @param tClass
     * @param <T>
     * @return
     */
    <T> T getBean(Class<T> tClass);

    /**
     *
     * @return
     */
    ApplicationContext getApplicationContext();
}
