package com.schemefinder;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.layout.*;

public class MainContentPanel extends VBox {

    private final SchemesTablePanel tablePanel;
    private final SchemeDetailPanel detailPanel;
    private final SearchPanel searchPanel;

    public MainContentPanel() {
        getStyleClass().add("main-content");
        setFillWidth(true);

        // 1. Header banner
        HeaderBanner banner = new HeaderBanner();
        VBox.setMargin(banner, new Insets(0, 0, 0, 0));

        // 2. Search/filter panel
        searchPanel = new SearchPanel();
        searchPanel.getStyleClass().add("search-section");

        // 3. Lower split area
        tablePanel = new SchemesTablePanel();
        detailPanel = new SchemeDetailPanel();

        HBox splitPane = new HBox(0);
        splitPane.getStyleClass().add("split-area");
        HBox.setHgrow(tablePanel, Priority.ALWAYS);
        splitPane.getChildren().addAll(tablePanel, detailPanel);
        VBox.setVgrow(splitPane, Priority.ALWAYS);
        VBox.setMargin(splitPane, new Insets(0, 16, 16, 16));

        getChildren().addAll(banner, searchPanel, splitPane);

        // Wire up interactions
        tablePanel.setOnSchemeSelect(detailPanel::showScheme);

        searchPanel.setOnSearch((query, category) -> {
            ObservableList<Scheme> results = SchemeRepository.search(query, category);
            tablePanel.loadSchemes(results);
            if (!results.isEmpty()) {
                detailPanel.showScheme(results.get(0));
            } else {
                detailPanel.showScheme(null);
            }
        });

        // Pre-populate with all schemes
        tablePanel.loadSchemes(SchemeRepository.getAllSchemes());
        detailPanel.showScheme(SchemeRepository.getAllSchemes().get(0));
    }
}
