package com.ks.hrms.core.component;

import com.jfoenix.controls.cells.editors.TextFieldEditorBuilder;
import com.jfoenix.controls.cells.editors.base.GenericEditableTreeTableCell;
import com.ks.hrms.core.component.ui.*;
import com.ks.hrms.core.component.ui.cells.*;
import com.ks.hrms.utils.Utils;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TreeTableCell;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;
import javafx.util.StringConverter;

import java.util.*;

public class FormFieldFactory {

    public static final String TRUE = "TRUE";

    private ArrayList<FormField> formFields;
    private LinkedHashMap<String, AbstractCustomParent> fieldMap;

    public FormFieldFactory addFormFields(ArrayList<FormField> formFields) {
        this.formFields = formFields;
        return this;
    }

    public void init() {
        fieldMap = new LinkedHashMap<>();
        for (FormField f : this.formFields) {
            parseAttributes(f);
        }
    }

    public static void parseField(FormField formField, AbstractCustomParent field) {
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
        fieldMap.put(formField.getId(), parseAttribute(formField));
    }

    public AbstractCustomParent parseAttribute(FormField formField) {

        switch (formField.getType()) {
            case FormField.ATTRIBUTE_TYPE_TEXTFIELD:
                CustomTextField textField = new CustomTextField(formField.getId(), formField.getCaption());
                textField = new CustomParentAdapter<CustomTextField>(textField, obj -> {
                    parseField(formField, obj);
                }).fine();
                return textField;

            case FormField.ATTRIBUTE_TYPE_PASSWORD:
                CustomPassWord password = new CustomPassWord(formField.getId(), formField.getCaption());
                password = new CustomParentAdapter<CustomPassWord>(password, obj -> {
                    parseField(formField, obj);
                }).fine();
                return password;

            case FormField.ATTRIBUTE_TYPE_TEXTAREA:
                CustomTextArea area = new CustomTextArea(formField.getId(), formField.getCaption());
                area = new CustomParentAdapter<CustomTextArea>(area, obj -> {
                    parseField(formField, obj);
                }).fine();
                return area;

            case FormField.ATTRIBUTE_TYPE_DATE:
                CustomDatePicker datePicker = new CustomDatePicker(formField.getId(), formField.getCaption(), CustomDatePicker.TYPE_DATE);
                datePicker = new CustomParentAdapter<CustomDatePicker>(datePicker, obj -> {
                    parseField(formField, obj);
                }).fine();
                return datePicker;

            case FormField.ATTRIBUTE_TYPE_DATETIME:
                datePicker = new CustomDatePicker(formField.getId(), formField.getCaption(), CustomDatePicker.TYPE_DATE_TIME);
                datePicker = new CustomParentAdapter<CustomDatePicker>(datePicker, obj -> {
                    parseField(formField, obj);
                }).fine();
                return datePicker;

            case FormField.ATTRIBUTE_TYPE_BUTTON:
                CustomButton button = new CustomButton(formField.getId(), formField.getCaption());
                button = new CustomParentAdapter<CustomButton>(button, obj -> {
                    parseField(formField, obj);
                }).fine();
                return button;

            case FormField.GROUP_BUTTON:
                CustomButtonGroup btnGroup = new CustomButtonGroup(formField.getId());
                btnGroup = new CustomParentAdapter<CustomButtonGroup>(btnGroup, obj -> {
                    obj.addButtons(formField.getItemList());
                    parseField(formField, obj);
                }).fine();
                return btnGroup;

            case FormField.GROUP_RADIO_BUTTON:
                CustomRadioBox radGroup = new CustomRadioBox(formField.getId(), formField.getCaption(), Utils.getValue(String.class, formField.getDefValue()));
                radGroup = new CustomParentAdapter<CustomRadioBox>(radGroup, obj -> {
                    obj.addSelect(formField.getItemList());
                    parseField(formField, obj);
                }).fine();
                return radGroup;

            case FormField.GROUP_CHECKBOX:
                CustomCheckBox checkGroup = new CustomCheckBox(formField.getId(), formField.getCaption());
                checkGroup = new CustomParentAdapter<CustomCheckBox>(checkGroup, obj -> {
                    obj.addSelect(formField.getItemList());
                    parseField(formField, obj);
                }).fine();
                return checkGroup;

            case FormField.GROUP_COMBOBOX:
                CustomComboBox comBoBox = new CustomComboBox(formField.getId(), formField.getCaption(), Utils.getValue(String.class, formField.getDefValue()));
                comBoBox = new CustomParentAdapter<CustomComboBox>(comBoBox, obj -> {
                    CustomComponentFactory.reloadComBoBox(obj, formField.getItemList());
                    parseField(formField, obj);
                }).fine();
                return comBoBox;

            default:
                return null;
        }
    }

    public TreeTableCell generateFieldBuilder(FormField formField) {
        switch (formField.getType()) {
            case FormField.ATTRIBUTE_TYPE_DATE:
            case FormField.ATTRIBUTE_TYPE_DATETIME:
                return null;
            case FormField.ATTRIBUTE_TYPE_BUTTON:
                return new GenericChoiceTreeTableCell(new ButtonBuilder(formField));
            case FormField.GROUP_CHECKBOX:
                return new GenericChoiceTreeTableCell(new CheckBoxBuilder(formField));
            case FormField.GROUP_COMBOBOX:
                return new GenericEditableTreeTableCell(new ComboBoxEditBuilder(formField));
            default:
                return new GenericEditableTreeTableCell(new TextFieldEditorBuilder());
        }
    }

    public Callback<TableColumn<Object, Object>, TableCell<Object, Object>> generateColumnCellFactory(TableColumn col, FormField formField) {

        class BuilderUtil {

            final StringConverter<Object> objConverter = new StringConverter<Object>() {
                @Override
                public String toString(Object t) {
                    return t == null ? null : t.toString();
                }

                @Override
                public Object fromString(String string) {
                    return string;
                }
            };

            String[] converterItemList(List<FormField.FormFieldAttribute> attributes) {
                Optional<List<FormField.FormFieldAttribute>> optional = Optional.<List<FormField.FormFieldAttribute>>of(
                        attributes
                );

                return optional.map(input -> {
                    String[] result = new String[input.size()];
                    for (int i = 0; i < input.size(); i++) {
                        result[i] = input.get(i).getValue();
                    }
                    return result;
                }).get();


            }
        }

        BuilderUtil util = new BuilderUtil();

        switch (formField.getType()) {
            case FormField.ATTRIBUTE_TYPE_DATE:
            case FormField.ATTRIBUTE_TYPE_DATETIME:
                return null;
            case FormField.ATTRIBUTE_TYPE_BUTTON:
            case FormField.GROUP_CHECKBOX:
                return CheckBoxTableCell.forTableColumn(col);
            case FormField.GROUP_COMBOBOX:
                return ComboBoxTableCell.forTableColumn(formField.getItemList());
            default:
                return TextFieldTableCell.forTableColumn(util.objConverter);
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

        private CustomParentAdapter(T t, DoCustomParent<T> toDo) {
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
