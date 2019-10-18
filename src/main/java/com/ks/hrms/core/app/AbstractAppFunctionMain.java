package com.ks.hrms.core.app;

import com.ks.hrms.core.component.form.AbstractForm;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public abstract class AbstractAppFunctionMain extends BorderPane implements FunctionMain, ToolBar.ButtonClickListener {

    public static final String STYLE_FUNCTION_CONTENT = "function_content";
    public static final String STYLE_FUNCTION_ROW_BOX = "function_row_box";
    public static final String STYLE_FUNCTION_COL_BOX = "function_col_box";

    protected Function function;
    protected ToolBar toolbar;

    private HashMap<DrawPoint, Node> drawPointHashMap;
    private TabPane root;

    public AbstractAppFunctionMain() {
        getStyleClass().add(STYLE_FUNCTION_CONTENT);
        root = new TabPane();
        fitToParent(this);
    }

    @Override
    public void init() {
        root.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        drawPointHashMap = new HashMap<>(16);
        toolbar = createToolbar();
        toolbar.setButtonClickListener(this);
    }

    protected void addComponent(int tab, int row, int col, Node component) {

        DrawPoint key = new DrawPoint(tab, row, col);

        if (!drawPointHashMap.containsKey(key)) {
            drawPointHashMap.put(key, component);
        }
    }

    protected void addComponent(int tab, Node component) {
        this.addComponent(tab, 0, 0, component);
    }

    /**
     * 根据坐标分布情况不同 界面不同
     */
    protected void draw() {
        root.getTabs().clear();
        toolbar.draw();

        Draws draws = new Draws();
        draws.init();
        draws.sorteds().forEach(e -> {
            draws.draw(e.getKey().tab, e.getKey().row, e.getKey().col, e.getValue());
        });

        setTop((HBox) toolbar);
        setCenter(root);
    }

    @Override
    public void setFunction(Function function) {
        this.function = function;
    }

    public abstract ToolBar createToolbar();

    public abstract ButtonType[] getToolbarTypes();

    @Override
    public void onClose(Callback call) {
        call.call(null);
    }

    @Override
    public void onOpen(Callback call) {
        call.call(null);
    }

    protected void fitToParent(Parent parent) {
        AnchorPane.setTopAnchor(parent, 0.0);
        AnchorPane.setBottomAnchor(parent, 0.0);
        AnchorPane.setLeftAnchor(parent, 0.0);
        AnchorPane.setRightAnchor(parent, 0.0);
    }

    class DrawPoint {

        private int tab;
        private int row;
        private int col;

        public DrawPoint(int tab, int row, int col) {
            this.tab = tab;
            this.row = row;
            this.col = col;
        }

        @Override
        public boolean equals(Object o) {

            if (this == o) {
                return true;
            }

            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            DrawPoint point = (DrawPoint) o;

            if (tab != point.tab) {
                return false;
            }
            if (row != point.row) {
                return false;
            }

            return col == point.col;
        }

        @Override
        public int hashCode() {
            int result = tab;
            result = 31 * result + row;
            result = 31 * result + col;
            return result;
        }
    }

    class Draws {
        /**
         * Tabs
         */
        final HashMap<Integer, Tab> tabMap = new HashMap<>(16);
        final HashMap<Tab, HashMap<Integer, HBox>> rowMap = new HashMap<>(16);
        final HashMap<HBox, HashMap<Integer, VBox>> colMap = new HashMap<>(16);


        public Draws() {

        }

        public void init() {

            sorteds().forEach(e -> {

                DrawPoint p = e.getKey();

                if (!tabMap.containsKey(p.tab)) {

                    Tab tab;
                    HBox rowBox;
                    VBox colBox;

                    HashMap<Integer, HBox> rows = new HashMap<>(16);
                    HashMap<Integer, VBox> cols = new HashMap<>(16);
                    rows.put(p.row, rowBox = generateRowBox());
                    cols.put(p.col, colBox = generateColBox());

                    tab = addTab(p.tab);
                    addRow(tab, rowBox);
                    rowBox.getChildren().add(colBox);
                    rowMap.put(tab, rows);
                    colMap.put(rowBox, cols);

                } else {
                    Tab tab = tabMap.get(p.tab);
                    changeCols(p.col, changeRows(p.row, tab));
                }
            });

        }

        private Tab addTab(int tab) {
            Tab result;
            tabMap.put(tab, result = new Tab());
            result.setContent(generateContentPane());
            root.getTabs().add(result);
            return result;
        }

        private HBox changeRows(int row, Tab tab) {
            HBox result;
            HashMap<Integer, HBox> rows = rowMap.get(tab);
            if (null != rows) {

                if (rows.containsKey(row)) {
                    return rows.get(row);
                } else {
                    rows.put(row, result = generateRowBox());
                    addRow(tab, result);
                    return result;
                }
            } else {
                rows = new HashMap<>(16);
                rowMap.put(tab, rows);
                rows.put(row, result = generateRowBox());
                addRow(tab, result);
            }
            return result;
        }

        private VBox changeCols(int col, HBox rowBox) {

            VBox result;
            HashMap<Integer, VBox> cols = colMap.get(rowBox);
            if (null != cols) {

                if (cols.containsKey(col)) {
                    return cols.get(col);
                } else {
                    cols.put(col, result = generateColBox());
                    rowBox.getChildren().add(result);
                    return result;
                }
            } else {
                cols = new HashMap<>(16);
                colMap.put(rowBox, cols);
                cols.put(col, result = generateColBox());
                rowBox.getChildren().add(result);
            }

            return result;
        }

        private void addRow(Tab tab, HBox rowBox) {
            SplitPane content = (SplitPane) tab.getContent();
            content.getItems().add(new ScrollPane(rowBox));
        }

        private HBox generateRowBox() {
            HBox row = new HBox();
            row.getStyleClass().add(STYLE_FUNCTION_ROW_BOX);
            return row;
        }

        private VBox generateColBox() {
            VBox col = new VBox();
            col.getStyleClass().add(STYLE_FUNCTION_COL_BOX);
            return col;
        }

        private SplitPane generateContentPane() {
            SplitPane splitPane = new SplitPane();
            splitPane.setOrientation(Orientation.VERTICAL);
            return splitPane;
        }

        public Stream<Map.Entry<DrawPoint, Node>> sorteds() {
            return drawPointHashMap.entrySet().stream().sorted((a, b) -> {
                return Comparator.comparingInt(value -> {
                    return 0;
                }).compare(a, b);
            });
        }

        void draw(int tab, int row, int col, Node node) {

            Tab content = tabMap.get(tab);

            if (null == content) {
                content = addTab(tab);
            }

            HBox rowBox = changeRows(row, content);
            VBox colBox = changeCols(col, rowBox);

            if (node instanceof AbstractForm) {
                content.setText(((AbstractForm) node).getCaption());
            }

            colBox.getChildren().add(node);
        }
    }
}
