package com.schemefinder;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import java.util.function.Consumer;

public class SchemesTablePanel extends VBox {

    private final TableView<Scheme> tableView;
    private final Label countLabel;
    private Consumer<Scheme> onSelect;

    @SuppressWarnings("unchecked")
    public SchemesTablePanel() {
        getStyleClass().add("schemes-table-panel");
        setSpacing(0);

        // Header
        HBox header = new HBox();
        header.setAlignment(Pos.CENTER_LEFT);
        header.setPadding(new Insets(16, 18, 12, 18));
        header.getStyleClass().add("table-panel-header");

        HBox titleRow = new HBox(10);
        titleRow.setAlignment(Pos.CENTER_LEFT);

        Label icon = new Label("🔖");
        icon.setStyle("-fx-font-size: 15px;");

        Label title = new Label("Matching Schemes");
        title.getStyleClass().add("panel-section-title");

        titleRow.getChildren().addAll(icon, title);
        HBox.setHgrow(titleRow, Priority.ALWAYS);

        countLabel = new Label("12 results");
        countLabel.getStyleClass().add("result-count");

        header.getChildren().addAll(titleRow, countLabel);

        Separator sep = new Separator();
        sep.getStyleClass().add("section-divider");

        // Table
        tableView = new TableView<>();
        tableView.getStyleClass().add("schemes-table");
        tableView.setPlaceholder(new Label("No schemes found. Try a different search term."));
        VBox.setVgrow(tableView, Priority.ALWAYS);

        TableColumn<Scheme, String> nameCol = new TableColumn<>("Scheme Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameCol.setPrefWidth(260);
        nameCol.setMinWidth(150);
        nameCol.setCellFactory(col -> new TableCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    Label lbl = new Label(item);
                    lbl.setWrapText(true);
                    lbl.setMaxWidth(240);
                    lbl.getStyleClass().add("table-name-cell");
                    setGraphic(lbl);
                    setText(null);
                }
            }
        });

        TableColumn<Scheme, String> categoryCol = new TableColumn<>("Category");
        categoryCol.setCellValueFactory(new PropertyValueFactory<>("category"));
        categoryCol.setPrefWidth(130);
        categoryCol.setMinWidth(90);
        categoryCol.setCellFactory(col -> new TableCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    Label badge = new Label(item);
                    badge.getStyleClass().add("category-badge-sm");
                    setGraphic(badge);
                    setText(null);
                }
            }
        });

        TableColumn<Scheme, String> eligibilityCol = new TableColumn<>("Eligibility");
        eligibilityCol.setCellValueFactory(new PropertyValueFactory<>("eligibility"));
        eligibilityCol.setPrefWidth(180);
        eligibilityCol.setMinWidth(120);
        eligibilityCol.setCellFactory(col -> new TableCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    Label lbl = new Label(item);
                    lbl.setWrapText(true);
                    lbl.setMaxWidth(170);
                    lbl.getStyleClass().add("table-eligibility-cell");
                    setGraphic(lbl);
                    setText(null);
                }
            }
        });

        tableView.getColumns().addAll(nameCol, categoryCol, eligibilityCol);
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        tableView.getSelectionModel().selectedItemProperty().addListener((obs, old, newVal) -> {
            if (onSelect != null && newVal != null) {
                onSelect.accept(newVal);
            }
        });

        tableView.setRowFactory(tv -> {
            TableRow<Scheme> row = new TableRow<>();
            row.getStyleClass().add("table-row");
            return row;
        });

        getChildren().addAll(header, sep, tableView);

        // Load all schemes initially
        loadSchemes(SchemeRepository.getAllSchemes());
    }

    public void loadSchemes(ObservableList<Scheme> schemes) {
        tableView.setItems(schemes);
        int count = schemes.size();
        countLabel.setText(count + (count == 1 ? " result" : " results"));

        if (!schemes.isEmpty()) {
            tableView.getSelectionModel().selectFirst();
        }
    }

    public void setOnSchemeSelect(Consumer<Scheme> handler) {
        this.onSelect = handler;
    }
}
