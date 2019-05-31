package com.ks.hrms.dao;

import org.springframework.stereotype.Repository;

@Repository
public class SystemDao extends BaseDao {

    static final String TABLE_NAME = "SYSTEM_INFO";
    static final String LOAD_SYS_INFO = "SELECT COMPANYNAME,VER,ABOUT FROM "+TABLE_NAME;
    static final String SAVE_SYS_INFO = "UPDATE "+TABLE_NAME+" SET COMPANYNAME = ?,VER = ?,ABOUT = ? WHERE ID = ?";

    public SystemInfo getSysInfo(){
        final SystemInfo info = new SystemInfo();
        this.getTemplate().query(LOAD_SYS_INFO,resultSet -> {
                 info.setCompanyName(resultSet.getString(1));
                 info.setVer(resultSet.getString(2));
                 info.setAbout(resultSet.getString(3));
        });
        return info;
    }

    public void update(SystemInfo sysInfo){
        this.getTemplate().update(SAVE_SYS_INFO,sysInfo.getCompanyName(),sysInfo.getVer(),sysInfo.getAbout(),"SYSTEM");
    }

    public static class SystemInfo{

        private String companyName;
        private String ver;
        private String about;

        public String getCompanyName() {
            return companyName;
        }

        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }

        public String getVer() {
            return ver;
        }

        public void setVer(String ver) {
            this.ver = ver;
        }

        public String getAbout() {
            return about;
        }

        public void setAbout(String about) {
            this.about = about;
        }
    }
}
