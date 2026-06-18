package com.schemefinder;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

public class SearchPanel extends HBox {

    private final TextField searchField;
    private final ComboBox<String> categoryCombo;
    private BiConsumer<String, String> onSearch;

    public SearchPanel() {
        getStyleClass().add("search-panel");
        setAlignment(Pos.CENTER_LEFT);
        setSpacing(16);
        setPadding(new Insets(20, 24, 20, 24));

        // Search label + field
        VBox searchBox = new VBox(6);
        Label searchLabel = new Label("Search Schemes");
        searchLabel.getStyleClass().add("input-label");

        searchField = new TextField();
        searchField.setPromptText("🔍  Search by name, category or eligibility...");
        searchField.getStyleClass().add("search-field");
        searchField.setPrefWidth(310);
        searchField.setPrefHeight(42);

        searchBox.getChildren().addAll(searchLabel, searchField);

        // Category label + combo
        VBox categoryBox = new VBox(6);
        Label categoryLabel = new Label("Category");
        categoryLabel.getStyleClass().add("input-label");

        List<String> cats = new ArrayList<>();
        cats.add("All Categories");
        cats.addAll(SchemeRepository.getCategories());
        categoryCombo = new ComboBox<>(FXCollections.observableArrayList(cats));
        categoryCombo.setValue("All Categories");
        categoryCombo.getStyleClass().add("category-combo");
        categoryCombo.setPrefWidth(200);
        categoryCombo.setPrefHeight(42);

        categoryBox.getChildren().addAll(categoryLabel, categoryCombo);

        // Buttons
        VBox btnBox = new VBox(6);
        Label btnSpacer = new Label(" ");
        btnSpacer.getStyleClass().add("input-label");

        HBox buttons = new HBox(10);
        buttons.setAlignment(Pos.CENTER_LEFT);

        Button searchBtn = new Button("🔍  Search");
        searchBtn.getStyleClass().add("btn-primary");
        searchBtn.setPrefHeight(42);
        searchBtn.setPrefWidth(110);

        Button clearBtn = new Button("↺  Clear");
        clearBtn.getStyleClass().add("btn-outline");
        clearBtn.setPrefHeight(42);
        clearBtn.setPrefWidth(100);

        buttons.getChildren().addAll(searchBtn, clearBtn);
        btnBox.getChildren().addAll(btnSpacer, buttons);

        getChildren().addAll(searchBox, categoryBox, btnBox);

        // Event handlers
        searchBtn.setOnAction(e -> triggerSearch());
        clearBtn.setOnAction(e -> {
            searchField.clear();
            categoryCombo.setValue("All Categories");
            triggerSearch();
        });
        searchField.setOnAction(e -> triggerSearch());
        categoryCombo.setOnAction(e -> triggerSearch());
    }

    private void triggerSearch() {
        if (onSearch != null) {
            onSearch.accept(searchField.getText(), categoryCombo.getValue());
        }
    }

    public void setOnSearch(BiConsumer<String, String> handler) {
        this.onSearch = handler;
    }

    public TextField getSearchField() {
        return searchField;
    }
}
