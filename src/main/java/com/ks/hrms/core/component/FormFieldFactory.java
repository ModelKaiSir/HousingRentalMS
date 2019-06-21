package com.ks.hrms.core.component;

import com.ks.hrms.core.component.ui.*;
import com.ks.hrms.utils.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class FormFieldFactory {

    public static final String TRUE = "TRUE";

    private ArrayList<FormField> formFields;
    private LinkedHashMap<String, AbstractCustomParent> fieldMap;

    public void addFormFields(ArrayList<FormField> formFields) {
        this.formFields = formFields;
        fieldMap = new LinkedHashMap<>();
        for (FormField f : this.formFields) {
            parseAttributes(f);
        }
    }

    private void parseField(FormField formField, AbstractCustomParent field) {
        field.setEditable(formField.isReadOnly());
        field.setRequired(formField.isRequired());
        field.setBreakable(formField.isBreak());
        field.setWidth(formField.getWidth());
        field.setHeight(formField.getHeight());
        field.setPos(formField.getPos());

        if (field instanceof AbstractCustomParent.InitValue) {
            ((AbstractCustomParent.InitValue) field).setInitValue(formField.getDefValue());
        }

    }

    public void parseAttributes(FormField formField) {

        System.out.println(formField.getId());
        switch (formField.getType()) {
            case FormField.ATTRIBUTE_TYPE_TEXTFIELD:
                CustomTextField textField =  new CustomTextField(formField.getId(), formField.getCaption());
                textField = new CustomParentAdapter<CustomTextField>(textField ,obj ->{
                    parseField(formField, obj);
                }).fine();
                fieldMap.put(formField.getId(), textField);
                break;
            case FormField.ATTRIBUTE_TYPE_BUTTON:
                CustomButton button = new CustomButton(formField.getId(), formField.getCaption());
                button = new CustomParentAdapter<CustomButton>(button,obj ->{
                    parseField(formField, obj);
                }).fine();

                fieldMap.put(formField.getId(), button);
                break;
            case FormField.GROUP_BUTTON:
                CustomButtonGroup btnGroup = new CustomButtonGroup(formField.getId());
                btnGroup = new CustomParentAdapter<CustomButtonGroup>(btnGroup,obj ->{
                    obj.addButtons(formField.getItemList());
                    parseField(formField, obj);
                }).fine();
                fieldMap.put(formField.getId(), btnGroup);
                break;
            case FormField.GROUP_RADIO_BUTTON:
                CustomRadioBox radGroup = new CustomRadioBox(formField.getId(), formField.getCaption(), Utils.getValue(String.class, formField.getDefValue()));
                radGroup = new CustomParentAdapter<CustomRadioBox>(radGroup,obj ->{
                    obj.addSelect(formField.getItemList());
                    parseField(formField, obj);
                }).fine();

                fieldMap.put(formField.getId(), radGroup);
                break;
            case FormField.GROUP_CHECKBOX:
                CustomCheckBox checkGroup = new CustomCheckBox(formField.getId(), formField.getCaption(), Utils.getValue(String.class, formField.getDefValue()));
                checkGroup = new CustomParentAdapter<CustomCheckBox>(checkGroup,obj ->{
                    obj.addSelect(formField.getItemList());
                    parseField(formField, obj);
                }).fine();
                fieldMap.put(formField.getId(), checkGroup);
                break;
            case FormField.GROUP_COMBOBOX:
                CustomComboBox comBoBox = new CustomComboBox(formField.getId(), formField.getCaption(), Utils.getValue(String.class, formField.getDefValue()));
                comBoBox = new CustomParentAdapter<CustomComboBox>(comBoBox, obj ->{
                    CustomComponentFactory.reloadComBoBox(obj, formField.getItemList());
                    parseField(formField, obj);
                }).fine();
                fieldMap.put(formField.getId(), comBoBox);
                break;
            default:
                break;
        }
    }

    public ArrayList<FormField> getFormFields() {
        return formFields;
    }

    public HashMap<String, AbstractCustomParent> getFieldMap() {
        return fieldMap;
    }


    class CustomParentAdapter<T extends AbstractCustomParent> {

        private T t;

        private CustomParentAdapter(T t,DoCustomParent<T> toDo) {
            this.t = t;
            this.t.init();
            toDo.toDo(t);
            this.t.afterInit();
        }

        public T fine() {
            return t;
        }

    }

    interface DoCustomParent<T extends AbstractCustomParent> {
        void toDo(T t);
    }
}
