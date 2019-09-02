package com.ks.hrms.controller;

import com.jfoenix.controls.JFXTextField;
import com.ks.hrms.utils.Utils;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.scene.control.Accordion;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

public class AppMenuAdapter {

    private Accordion accordion;
    private JFXTextField searchField;

    private TitledPane cachePane;
    private SimpleStringProperty searchText = new SimpleStringProperty();
    private FilterService service = new FilterService();

    public AppMenuAdapter(Accordion accordion, JFXTextField searchField) {
        this.accordion = accordion;
        this.searchField = searchField;
    }

    public void addFilterListener(EventHandler<? super MouseEvent> eventHandler) {

        searchResultPane(eventHandler);
        this.accordion.getPanes().add(cachePane);
        searchText.bind(searchField.textProperty());
        searchText.addListener((ob,old,nv) ->{
            service.restart();
            this.accordion.setExpandedPane(cachePane);
        });

        service.exceptionProperty().addListener((ob,ol,nv) ->{
            nv.printStackTrace();
        });

    }

    private void searchResultPane(EventHandler<? super MouseEvent> eventHandler) {
        cachePane = new TitledPane();
        cachePane.setText("查找结果");
        ListView<AppMenuItem> cacheItems = new ListView<>();
        cacheItems.itemsProperty().bind(service.valueProperty());
        cacheItems.setOnMouseClicked(eventHandler);
        cachePane.setContent(cacheItems);
    }

    class FilterService extends Service<ObservableList<AppMenuItem>>{

        @Override
        protected Task<ObservableList<AppMenuItem>> createTask() {
            return generateTask();
        }

        private Task<ObservableList<AppMenuItem>> generateTask(){

            return new Task<ObservableList<AppMenuItem>>() {

                FilteredList<AppMenuItem> generateFilteredList(ObservableList<AppMenuItem> list){
                    return new FilteredList<AppMenuItem>(list,v ->{
                        AppMenuItem item = (AppMenuItem) v;
                        String str = searchText.get().trim();
                        if(Utils.isEmpty(str)){
                            return false;
                        }

                        if (item.getMenuName().contains(str) || item.getFunctionId().contains(str)) {
                            return true;
                        }
                        return false;
                    });
                }

                @Override
                protected ObservableList<AppMenuItem> call() throws Exception {
                    ArrayList result = new ArrayList();
                    accordion.getPanes().stream().forEach(pane ->{
                        ListView<AppMenuItem> view = (ListView<AppMenuItem>) pane.getContent();
                        if(null!=view.getItems()){
                            result.addAll(new ArrayList<>(view.getItems()));
                        }

                    });

                    FilteredList<AppMenuItem> items = generateFilteredList(FXCollections.observableArrayList(result));
                    updateValue(items);
                    succeeded();
                    return items;
                }
            };
        }
    }
}
