package com.ks.hrms.core.component;

import javafx.geometry.Pos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FormField {

    static final String TRUE = "TRUE";

    /**
     * 组件的类型
     */
    public static final String ATTRIBUTE_TYPE = "TYPE";

    /**
     * itemList
     */
    public static final String ATTRIBUTE_ITEM_LIST = "ItemList";

    /**
     * 换行
     */
    public static final String ATTRIBUTE_BREAK = "BREAK";
    /**
     * 宽
     */
    public static final String ATTRIBUTE_WIDTH = "W";
    /**
     * 高
     */
    public static final String ATTRIBUTE_HEIGHT = "H";
    /**
     * 只读
     */
    public static final String ATTRIBUTE_READONLY = "RED";
    /**
     * 必填
     */
    public static final String ATTRIBUTE_REQ = "REQ";
    public static final String ATTRIBUTE_ALIGNMENT = "ALIGNMENT";
    /**
     * 默认值
     */
    public static final String ATTRIBUTE_DEF_VALUE = "DEFV";

    public static final String ATTRIBUTE_TYPE_TEXTFIELD = "TYPE=TEXTFIELD;";
    public static final String TYPE_TEXTFIELD = "TEXTFIELD";
    public static final String ATTRIBUTE_TYPE_BUTTON = "TYPE=BUTTON;";
    public static final String TYPE_BUTTON = "BUTTON";
    public static final String ATTRIBUTE_TYPE_SELECT_FIELD = "TYPE=SELECT;";
    public static final String TYPE_SELECT = "SELECT";
    public static final String ATTRIBUTE_TYPE_CHECKBOX = "TYPE=CHECKBOX;";
    public static final String TYPE_CHECKBOX = "CHECKBOX";
    public static final String ATTRIBUTE_TYPE_RADIO_BOX = "TYPE=RADIOBUTTON;";
    public static final String TYPE_RADIO_BTN = "RADIOBUTTON";
    public static final String ATTRIBUTE_TYPE_GROUP_BUTTON = "TYPE=GROUP_BUTTON;";
    public static final String GROUP_BUTTON = "GROUP_BUTTON";
    public static final String GROUP_TEXTFIELD = "GROUP_TEXTFIELD";
    public static final String GROUP_RADIO = "GROUP_RADIO";
    public static final String ATTRIBUTE_TYPE_GROUP_RADIO = "TYPE=GROUP_RADIO;";
    public static final String GROUP_CHECKBOX = "GROUP_CHECKBOX";
    public static final String ATTRIBUTE_TYPE_GROUP_CHECKBOX = "TYPE=GROUP_CHECKBOX;";
    public static final String GROUP_COMPONENT = "GROUP_COMPONENT";

    public static final String ATTRIBUTE_VALUE_BREAK = "BREAK=TRUE;";
    public static final String ATTRIBUTE_VALUE_READONLY = "RED=TRUE;";
    public static final String ATTRIBUTE_VALUE_REQ = "REQ=TRUE;";

    /**
     * 字段
     */
    private String id;
    /**
     * 描述
     */
    private String caption;
    /**
     * 配置
     */
    private String[] attributes;

    public FormField(String id, String caption, String attribute) {
        this.id = id;
        this.caption = caption;
        this.attributes = attribute.split(";");
    }

    public static String putWidth(String v) {
        return ATTRIBUTE_WIDTH + "=" + v + ";";
    }

    public static String putHeight(String v) {
        return ATTRIBUTE_HEIGHT + "=" + v + ";";
    }

    public static String putDefValue(Object value) {
        return ATTRIBUTE_DEF_VALUE + "=" + value + ";";
    }

    private static String loadItemList(List<FormFieldAttribute> fs){
        String result = "";
        for (FormFieldAttribute f:fs){
            result += f.key+"\t"+f.value+"\n";
        }

        return result;
    }

    public static String putItemList(FormFieldAttribute[] fs){
        return putItemList(ATTRIBUTE_ITEM_LIST,Arrays.asList(fs));
    }

    public static String putItemList(ArrayList<FormFieldAttribute> formFieldAttributeArrayList){
        return putItemList(ATTRIBUTE_ITEM_LIST,formFieldAttributeArrayList);
    }

    public static String putItemList(String key,List<FormFieldAttribute> formFieldAttributeArrayList){
        return key+"="+loadItemList(formFieldAttributeArrayList)+";";
    }

    public static String putComponentAlignment(Pos p) {
        return ATTRIBUTE_ALIGNMENT + "=" + p + ";";
    }

    private String getAttributeValue(String attribute) {
        return attribute.split("=")[1];
    }

    private String getAttributeKey(String attribute) {
        return attribute.split("=")[0];
    }

    public List<FormFieldAttribute> getItemList(){
        String a = getAttribute(ATTRIBUTE_ITEM_LIST,null);
        if(null!=a){
            return getItemList(a);
        }else{
            return null;
        }
    }

    public List<FormFieldAttribute> getItemList(String attribute){
        List<FormFieldAttribute> result = Stream.of(getAttributeValue(attribute).split("\n")).map(i ->{
            String[] a = i.split("\t");
            return new FormFieldAttribute(a[0],a[1]);
        }).collect(Collectors.toList());
        return result;
    }

    public Pos getPos(){
        String attribute = getAttribute(ATTRIBUTE_ALIGNMENT,null);
        if(null==attribute){
            return null;
        }
        return Pos.valueOf(getAttributeValue(attribute));
    }

    public Object getDefValue(){
        String attribute = getAttribute(ATTRIBUTE_DEF_VALUE,null);
        if(null==attribute){
            return null;
        }
        return getAttributeValue(attribute);
    }

    public String getType(){
        String attribute = getAttribute(ATTRIBUTE_TYPE,null);
        if(null==attribute){
            return null;
        }
        return getAttributeValue(attribute);
    }

    public double getHeight(){
        String attribute = getAttribute(ATTRIBUTE_HEIGHT,null);
        if(null==attribute){
            return 0;
        }
        return new Double(getAttributeValue(attribute));
    }

    public double getWidth(){
        String attribute = getAttribute(ATTRIBUTE_WIDTH,null);
        if(null==attribute){
            return 0;
        }
        return new Double(getAttributeValue(attribute));
    }

    public boolean isBreak() {
        String attribute = getAttribute(ATTRIBUTE_BREAK,null);
        if(null==attribute){
            return false;
        }
        return TRUE.equals(attribute.split("=")[1]);
    }

    public boolean isReadOnly(){
        String attribute = getAttribute(ATTRIBUTE_READONLY,null);
        if(null==attribute){
            return false;
        }
        return TRUE.equals(attribute.split("=")[1]);
    }

    public boolean isRequired(){
        String attribute = getAttribute(ATTRIBUTE_VALUE_REQ,null);
        if(null==attribute){
            return false;
        }
        return TRUE.equals(attribute.split("=")[1]);
    }

    public String getAttribute(String attributeType,String def){
        for (String a : attributes) {
            String key = getAttributeKey(a);
            if(key.equals(attributeType)){
                return a;
            }
        }

        return def;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public static void main(String[] args) {
        ArrayList<FormFieldAttribute> fs = new ArrayList<>();
        fs.add(new FormFieldAttribute("男","1"));
        fs.add(new FormFieldAttribute("女","0"));
        FormField f = new FormField("12","23",putItemList(fs));
        for (String a:f.getAttributeValue(f.getAttribute(ATTRIBUTE_ITEM_LIST,"")).split("\n")){
            System.out.println(Arrays.deepToString(a.split("\t")));
        }
    }

    public static class FormFieldAttribute{

        private String key;
        private String value;

        public FormFieldAttribute(String key, String value) {
            this.key = key;
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
