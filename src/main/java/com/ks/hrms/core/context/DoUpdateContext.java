package com.ks.hrms.core.context;

import com.ks.hrms.core.update.SystemUpdate;
import com.ks.hrms.core.update.UpdateLog;
import javafx.scene.control.Alert;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DoUpdateContext extends DoSystemContext implements UpdateContext {

    private boolean shouldUpdate;
    private List<String> messages;
    @Autowired
    private List<SystemUpdate> updates;

    private void showMessage(String headerText){
        //显示更新日志
        Alert dialog = new Alert(Alert.AlertType.INFORMATION);
        dialog.setTitle("更新日志");
        dialog.setHeaderText(headerText);
        dialog.setContentText((messages.stream().map(i ->{
            return i+"\r\n";
        }).reduce("",String::concat)));

        dialog.show();
        dialog.setResizable(true);
    }

    @Override
    public void update() {
        messages = new ArrayList<>();
        List<SystemUpdate> updates_sort = updates.stream().sorted((a,b) ->{
            return b.getVer().compareTo(a.getVer());
        }).collect(Collectors.toList());

        String headerText = "";
        int j = 0;

        for (SystemUpdate update:updates_sort){
            if(checkVer(update.getVer())){
                if(update instanceof UpdateLog){
                    String key = update.getDateTimeStr()+" 版本："+update.getVer();
                    if(j==0){
                        setVer(update.getVer());
                        headerText = "<<<"+key+">>>";
                        messages.addAll(((UpdateLog) update).getLogs());
                    }else{
                        messages.add("\r\n-----"+key+"-----\r\n");
                        messages.addAll(((UpdateLog) update).getLogs());
                    }
                    j++;
                }

                shouldUpdate = true;
                update.update();
            }
        }

        if(shouldUpdate){
            showMessage(headerText);
            updateSystemInfo();
        }
    }
}
