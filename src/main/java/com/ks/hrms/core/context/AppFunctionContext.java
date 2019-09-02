package com.ks.hrms.core.context;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.InputStream;

public abstract class AppFunctionContext extends DoUpdateContext {

    static final String applicationContextConfig = "classpath:spring.xml";
    static final String FXML_PATH = "UI/";
    private ApplicationContext applicationContext;


    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public void init(){
        initSystem();
        update();
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public <T> T getBean(String beanName,Class<T> tClass){
        return applicationContext.getBean(beanName,tClass);
    }

    public <T> T getBean(Class<T> tClass){
        return applicationContext.getBean(tClass);
    }

    public static Resource getResource(String name){
        Resource resource = new ClassPathResource(FXML_PATH+name);
        return resource;
    }

    public static Parent loadFXML(String name){
        try {

            return FXMLLoader.load(getResource(name).getURL());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Parent loadFXML(String name , Initializable controller){
        FXMLLoader loader = new FXMLLoader();
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        Resource resource = getResource(name);
        // 设置路径基准
        InputStream in = null;
        try {
            loader.setLocation(resource.getURL());
            loader.setController(controller);
            in = resource.getInputStream();
            // 对象方法的参数是InputStream，返回值是Object
            return (Parent) loader.load(in);
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    public static Initializable replaceSceneContent(Stage stage,String name) throws Exception {

        FXMLLoader loader = new FXMLLoader();
        // 设置BuilderFactory
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        Resource resource =getResource(name);
        // 设置路径基准
        loader.setLocation(resource.getURL());
        InputStream in = null;
        try {
            in = resource.getInputStream();
            // 对象方法的参数是InputStream，返回值是Object
            Parent page = (Parent) loader.load(in);
            stage.setScene(new Scene(page));
            // 可以得到Controller
            return (Initializable) loader.getController();
        } finally {
            in.close();
        }
    }
}
