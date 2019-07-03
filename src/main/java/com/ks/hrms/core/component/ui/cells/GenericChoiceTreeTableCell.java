package com.ks.hrms.core.component.ui.cells;

import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.cells.editors.base.JFXTreeTableCell;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.ks.hrms.core.component.form.Item;
import com.ks.hrms.core.component.ui.AbstractCustomParent;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;
import java.util.List;

public class GenericChoiceTreeTableCell<S extends Item, T> extends JFXTreeTableCell<S, T> {

    protected ChoiceNodeBuilder<S, T> builder;
    protected AbstractCustomParent node;


    /**
     * 直接在TableView上显示的控件 不是双击编辑的控件
     *
     * @param builder
     */
    public GenericChoiceTreeTableCell(ChoiceNodeBuilder<S, T> builder) {
        this.builder = builder;
    }

    /**
     * Any action attempting to commit an edit should call this method rather than commit the edit directly itself. This
     * method will perform any validation and conversion required on the value. For text values that normally means this
     * method just commits the edit but for numeric values, for example, it may first parse the given input. <p> The
     * only situation that needs to be treated specially is when the field is losing focus. If you user hits enter to
     * commit the cell with bad data we can happily cancel the commit and force them to enter a real value. If they
     * click away from the cell though we want to give them their old value back.
     *
     * @param losingFocus true if the reason for the call was because the field is losing focus.
     */
    protected void commitHelper(boolean losingFocus) {
        if (node == null) {
            return;
        }
        try {
            builder.validateValue();
            commitEdit((T) builder.getValue());
        } catch (Exception ex) {
            //Most of the time we don't mind if there is a parse exception as it
            //indicates duff user data but in the case where we are losing focus
            //it means the user has clicked away with bad data in the cell. In that
            //situation we want to just cancel the editing and show them the old
            //value.
            if (losingFocus) {
                cancelEdit();
            }
        }

    }

    private void setValue(T t) {
        setItem(t);
    }

    /**
     * Provides the string representation of the value of this cell when the cell is not being edited.
     */
    protected T getValue() {
        return getItem() == null ? null : getItem();
    }

    @Override
    public void startEdit() {
        System.out.println("startEdit");
        if (node == null) {
            createNode();
        }

        if (isEditable() && checkGroupedColumn()) {
            super.startEdit();
        }

        setGraphic(node.content());
        builder.setValue(getValue());
        setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
    }

    @Override
    public void cancelEdit() {
        super.cancelEdit();
        builder.cancelEdit();
        setGraphic(node.content());
        setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
    }

    /**
     * only allows editing for items that are not grouped
     *
     * @return whether the item is grouped or not
     */
    private boolean checkGroupedColumn() {
        boolean allowEdit = true;
        if (getTreeTableRow().getTreeItem() != null) {

            Object rowObject = getTreeTableRow().getTreeItem().getValue();

            if (rowObject instanceof RecursiveTreeObject && rowObject.getClass() == RecursiveTreeObject.class) {
                allowEdit = false;
            } else {
                // check grouped columns in the tableview
                if (getTableColumn() instanceof JFXTreeTableColumn && ((JFXTreeTableColumn) getTableColumn()).isGrouped()) {
                    // make sure that the object is a direct child to a group node
                    if (getTreeTableRow().getTreeItem().getParent() != null &&
                            getTreeTableRow().getTreeItem().getParent().getValue().getClass() == RecursiveTreeObject.class) {
                        allowEdit = false;
                    }
                }
            }
        }
        return allowEdit;
    }

    @Override
    public void updateItem(T item, boolean empty) {
        super.updateItem(item, empty);

        if (empty) {
            setText(null);
            setGraphic(null);
        } else {

            if (null == node) {
                createNode();
            }

            if (isEditing() && checkGroupedColumn()) {
                setGraphic(node.content());
                setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                builder.updateItem(item, empty);
            } else {
                setGraphic(node.content());
                setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                setText(null);
            }
        }
    }

    private void createNode() {

        EventHandler<KeyEvent> keyEventsHandler = t -> {

            if (t.getCode() == KeyCode.ENTER) {
                commitHelper(false);
            } else if (t.getCode() == KeyCode.ESCAPE) {
                cancelEdit();
            } else if (t.getCode() == KeyCode.TAB) {
                commitHelper(false);

                TreeTableColumn nextColumn = getNextColumn(!t.isShiftDown());
                if (nextColumn != null) {
                    getTreeTableView().edit(getIndex(), nextColumn);
                }
            }
        };

        ChangeListener<Boolean> focusChangeListener = (observable, oldValue, newValue) -> {
            getTreeTableView().getSelectionModel().select(getIndex());
            TreeItem<S> item = getTreeTableView().getSelectionModel().getSelectedItem();

            if (null == item) {
                //select Item
                getTreeTableView().getSelectionModel().select(getIndex());
                return;
            }

            if (node != null && !newValue) {
                commitHelper(true);
            }
        };

        node = builder.createNode(getValue(), keyEventsHandler, focusChangeListener);
        node.setEditable(getTreeTableView().isEditable());
        node.setDisable(!getTreeTableView().isEditable());
    }

    /**
     * @param forward true gets the column to the right, false the column to the left of the current column
     * @return
     */
    private TreeTableColumn<S, ?> getNextColumn(boolean forward) {
        List<TreeTableColumn<S, ?>> columns = new ArrayList<>();
        for (TreeTableColumn<S, ?> column : getTreeTableView().getColumns()) {
            columns.addAll(getLeaves(column));
        }
        //There is no other column that supports editing.
        if (columns.size() < 2) {
            return null;
        }
        int nextIndex = columns.indexOf(getTableColumn());
        if (forward) {
            nextIndex++;
            if (nextIndex > columns.size() - 1) {
                nextIndex = 0;
            }
        } else {
            nextIndex--;
            if (nextIndex < 0) {
                nextIndex = columns.size() - 1;
            }
        }
        return columns.get(nextIndex);
    }


    private List<TreeTableColumn<S, ?>> getLeaves(TreeTableColumn<S, ?> root) {
        List<TreeTableColumn<S, ?>> columns = new ArrayList<>();
        if (root.getColumns().isEmpty()) {
            //We only want the leaves that are editable.
            if (root.isEditable()) {
                columns.add(root);
            }
            return columns;
        } else {
            for (TreeTableColumn<S, ?> column : root.getColumns()) {
                columns.addAll(getLeaves(column));
            }
            return columns;
        }
    }

}
