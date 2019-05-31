package com.ks.hrms.core;

import com.ks.hrms.dao.SystemDao;
import javafx.beans.property.SimpleStringProperty;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

public class DoSystemContext implements SystemContext {

    static final String DEF_VER  = "1.0.1";

    private SimpleStringProperty ver = new SimpleStringProperty(DEF_VER);
    private SimpleStringProperty systemName = new SimpleStringProperty();
    private SimpleStringProperty about = new SimpleStringProperty();

    private SystemDao dao;
    private SystemDao.SystemInfo info;

    public DoSystemContext() {

    }

    public void initSystem(){
        info = dao.getSysInfo();
        systemName.set(info.getCompanyName());
        ver.set(info.getVer());
        about.set(info.getAbout());
    }

    public String getVer() {
        return ver.get();
    }

    @Override
    public SimpleStringProperty verProperty() {
        return ver;
    }

    public void setVer(String ver) {
        info.setVer(ver);
        this.ver.set(ver);
    }

    public String getSystemName() {
        return systemName.get();
    }

    @Override
    public SimpleStringProperty systemNameProperty() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        info.setCompanyName(systemName);
        this.systemName.set(systemName);
    }

    public String getAbout() {
        return about.get();
    }

    @Override
    public SimpleStringProperty aboutProperty() {
        return about;
    }

    public void setAbout(String about) {
        info.setAbout(about);
        this.about.set(about);
    }

    @Autowired
    public void setDao(SystemDao dao) {
        this.dao = dao;
    }

    public boolean checkVer(String ver) {
        return ver.compareTo(this.ver.get()) > 0;
    }

    public void updateSystemInfo(){
        dao.update(info);
    }
}
