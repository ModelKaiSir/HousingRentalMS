package com.ks.hrms.core.update;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class FirstUpdate implements SystemUpdate,UpdateLog {

    @Override
    public String getVer() {
        return "1.1.2";
    }

    @Override
    public void update() {
    }

    @Override
    public List<String> getLogs() {
        return Stream.of("1：本次更新内容，对整体代码使用spring进行了重构。").collect(Collectors.toList());
    }

    @Override
    public String getDateTimeStr(){
        return "更新时间：2019-04-28";
    }


}
