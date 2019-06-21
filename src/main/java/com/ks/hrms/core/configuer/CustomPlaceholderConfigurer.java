package com.ks.hrms.core.configuer;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CustomPlaceholderConfigurer extends PropertyPlaceholderConfigurer {

    static final String SQLITE_DRIVER = "org.sqlite.JDBC";
    static final String SQL_LITE = "sqliteUrl";
    static final String DATABASE_NAME = "config\\data.db";
    final String header = "jdbc:sqlite:";

    private String sysPath;

    private boolean isInit = true;

    @Override
    protected String convertProperty(String propertyName, String propertyValue) {
        if(propertyName.equals(SQL_LITE)){
            return getSqliteDb(header+sysPath+"\\"+DATABASE_NAME);
        }
        return super.convertProperty(propertyName, propertyValue);
    }

    /**
     * 检查是否存在sqliteDb文件 没有则创建
     * @return
     */
    private String getSqliteDb(String url){
        try {
            String path = url.substring(header.length(),url.length());
            initDb(path,url);
        } catch (ClassNotFoundException e) {
            Logger.getRootLogger().trace("没有找到sqlite驱动");
        } catch (SQLException e) {
            Logger.getRootLogger().trace("创建数据库失败！");
            e.printStackTrace();
        }

        return url;
    }

    private void initDb(String path, String url) throws ClassNotFoundException,SQLException{
        Resource resource = new FileSystemResource(path);
        Class.forName(SQLITE_DRIVER);
        if(!resource.exists()){
            Logger.getRootLogger().info("数据库文件不存在，创建数据库。");
            isInit = true;
            Connection connection = DriverManager.getConnection(url);
            if(null!=connection){
                connection.close();
            }
        }else{
            isInit = false;
        }
    }

    public String getSysPath() {
        return sysPath;
    }

    public void setSysPath(String sysPath) {
        this.sysPath = sysPath;
    }

    public boolean getIsInit() {
        return isInit;
    }

    public void setInit(boolean init) {
        isInit = init;
    }
}
