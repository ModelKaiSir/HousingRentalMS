package com.ks.hrms.core.component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class FormFieldFactory {

    public static final String TRUE = "TRUE";

    private ArrayList<FormField> formFields;
    private LinkedHashMap<String, AbstractField> fieldMap;

    public void addFormFields(ArrayList<FormField> formFields) {
        this.formFields = formFields;
        fieldMap = new LinkedHashMap<>();
        for (FormField f : this.formFields) {
            parseAttributes(f);
        }
    }

    private void parseField(FormField formField, AbstractField field) {
        field.setReadOnly(formField.isReadOnly());
        field.setRequired(formField.isRequired());
        field.setBreak(formField.isBreak());
        field.setWidth(formField.getWidth());
        field.setHeight(formField.getHeight());
        field.setPos(formField.getPos());
        field.setDefaultValue(formField.getDefValue());
    }

    public void parseAttributes(FormField formField) {

        System.out.println(formField.getId());
        switch (formField.getType()) {
            case FormField.TYPE_TEXTFIELD:
                CustomTextField textField = new CustomTextField(formField.getId(), formField.getCaption());
                parseField(formField, textField);
                fieldMap.put(formField.getId(), textField);
                break;
            case FormField.TYPE_BUTTON:
                CustomButton button = new CustomButton(formField.getId(), formField.getCaption());
                parseField(formField, button);
                fieldMap.put(formField.getId(), button);
                break;
            case FormField.GROUP_BUTTON:
                CustomButtonGroup btnGroup = new CustomButtonGroup(formField.getId());
                btnGroup.addButtons(formField.getItemList());
                parseField(formField, btnGroup);
                fieldMap.put(formField.getId(), btnGroup);
                break;
            case FormField.GROUP_RADIO:
                CustomRadioBox radGroup = new CustomRadioBox(formField.getId(),formField.getCaption(),formField.getDefValue());
                radGroup.addSelect(formField.getItemList());
                parseField(formField, radGroup);
                fieldMap.put(formField.getId(), radGroup);
                break;
            case FormField.GROUP_CHECKBOX:
                CustomCheckBox checkGroup = new CustomCheckBox(formField.getId(),formField.getCaption(),formField.getDefValue());
                checkGroup.addSelect(formField.getItemList());
                parseField(formField,checkGroup);
                fieldMap.put(formField.getId(),checkGroup);
            default:
                break;
        }
    }

    public ArrayList<FormField> getFormFields() {
        return formFields;
    }

    public HashMap<String, AbstractField> getFieldMap() {
        return fieldMap;
    }
}
