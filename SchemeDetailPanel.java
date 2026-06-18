package com.schemefinder;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.HashSet;
import java.util.Set;

public class SchemeDetailPanel extends VBox {

    private Label schemeNameLabel;
    private Label categoryBadge;
    private Label descriptionText;
    private Label benefitValue;
    private Label eligibilityValue;
    private Label documentsValue;
    private Label applyLink;
    private ToggleButton favoriteBtn;
    private VBox detailContent;
    private ScrollPane detailScroll;
    private VBox emptyState;
    private final Set<String> favorites = new HashSet<>();

    public SchemeDetailPanel() {
        getStyleClass().add("detail-panel");
        setPrefWidth(370);
        setMinWidth(280);

        buildHeader();
        buildContent();
        buildEmptyState();

        // Show empty state by default
        showEmpty();
    }

    private void buildHeader() {
        HBox header = new HBox();
        header.setAlignment(Pos.CENTER_LEFT);
        header.setPadding(new Insets(18, 18, 14, 18));
        header.getStyleClass().add("detail-header");

        HBox titleRow = new HBox(10);
        titleRow.setAlignment(Pos.CENTER_LEFT);

        Label icon = new Label("📋");
        icon.setStyle("-fx-font-size: 16px;");

        Label title = new Label("Scheme Details");
        title.getStyleClass().add("panel-section-title");

        titleRow.getChildren().addAll(icon, title);
        HBox.setHgrow(titleRow, Priority.ALWAYS);

        favoriteBtn = new ToggleButton("♡");
        favoriteBtn.getStyleClass().add("favorite-btn");
        favoriteBtn.setSelected(false);
        favoriteBtn.setOnAction(e -> {
            if (favoriteBtn.isSelected()) {
                favoriteBtn.setText("♥");
                favoriteBtn.getStyleClass().removeAll("favorite-btn");
                favoriteBtn.getStyleClass().add("favorite-btn-active");
            } else {
                favoriteBtn.setText("♡");
                favoriteBtn.getStyleClass().removeAll("favorite-btn-active");
                favoriteBtn.getStyleClass().add("favorite-btn");
            }
        });

        header.getChildren().addAll(titleRow, favoriteBtn);
        getChildren().add(header);

        // Divider
        Separator sep = new Separator();
        sep.getStyleClass().add("section-divider");
        getChildren().add(sep);
    }

    private void buildContent() {
        detailContent = new VBox(0);
        detailContent.getStyleClass().add("detail-content-wrapper");

        detailScroll = new ScrollPane(detailContent);
        detailScroll.setFitToWidth(true);
        detailScroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        detailScroll.getStyleClass().add("detail-scroll");
        VBox.setVgrow(detailScroll, Priority.ALWAYS);

        // Scheme name
        schemeNameLabel = new Label();
        schemeNameLabel.getStyleClass().add("detail-scheme-name");
        schemeNameLabel.setWrapText(true);

        // Category badge
        categoryBadge = new Label();
        categoryBadge.getStyleClass().add("category-badge");

        HBox badgeRow = new HBox(8);
        badgeRow.setAlignment(Pos.CENTER_LEFT);
        badgeRow.setPadding(new Insets(8, 18, 14, 18));
        badgeRow.getChildren().add(categoryBadge);

        VBox nameBox = new VBox(0);
        nameBox.setPadding(new Insets(18, 18, 0, 18));
        nameBox.getChildren().add(schemeNameLabel);

        detailContent.getChildren().addAll(nameBox, badgeRow);

        // Description card
        detailContent.getChildren().add(buildDetailCard("📝 About", null, null, true));

        // Benefit card
        detailContent.getChildren().add(buildDetailCard("💰 Benefit / Amount", null, null, false));

        // Eligibility card
        detailContent.getChildren().add(buildDetailCard("✅ Eligibility", null, null, false));

        // Documents card
        detailContent.getChildren().add(buildDetailCard("📄 Required Documents", null, null, false));

        // Apply card
        detailContent.getChildren().add(buildApplyCard());

        getChildren().add(detailScroll);
    }

    private VBox buildDetailCard(String sectionTitle, String value, String styleClass, boolean isDescription) {
        VBox card = new VBox(6);
        card.getStyleClass().add("detail-card");
        card.setPadding(new Insets(14, 18, 14, 18));

        Label title = new Label(sectionTitle);
        title.getStyleClass().add("detail-card-title");

        Label valueLabel = new Label(value != null ? value : "");
        valueLabel.setWrapText(true);
        valueLabel.getStyleClass().add("detail-card-value");

        card.getChildren().addAll(title, valueLabel);

        // Store reference
        if (isDescription) {
            descriptionText = valueLabel;
        } else if (sectionTitle.contains("Benefit")) {
            benefitValue = valueLabel;
        } else if (sectionTitle.contains("Eligibility")) {
            eligibilityValue = valueLabel;
        } else if (sectionTitle.contains("Documents")) {
            documentsValue = valueLabel;
        }

        return card;
    }

    private VBox buildApplyCard() {
        VBox card = new VBox(10);
        card.getStyleClass().add("detail-card");
        card.setPadding(new Insets(14, 18, 18, 18));

        Label title = new Label("🔗 How to Apply");
        title.getStyleClass().add("detail-card-title");

        applyLink = new Label();
        applyLink.setWrapText(true);
        applyLink.getStyleClass().add("apply-link");

        Button applyBtn = new Button("Apply Now →");
        applyBtn.getStyleClass().add("btn-primary");
        applyBtn.setPrefWidth(Double.MAX_VALUE);
        applyBtn.setPrefHeight(38);

        card.getChildren().addAll(title, applyLink, applyBtn);
        return card;
    }

    private void buildEmptyState() {
        emptyState = new VBox(16);
        emptyState.setAlignment(Pos.CENTER);
        emptyState.setPadding(new Insets(60, 24, 40, 24));
        VBox.setVgrow(emptyState, Priority.ALWAYS);

        Label icon = new Label("📋");
        icon.setStyle("-fx-font-size: 48px;");

        Label msg = new Label("Select a scheme to\nview its details");
        msg.getStyleClass().add("empty-state-text");
        msg.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);

        Label hint = new Label("Click any row in the Matching Schemes table");
        hint.getStyleClass().add("empty-state-hint");
        hint.setWrapText(true);
        hint.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);

        emptyState.getChildren().addAll(icon, msg, hint);
        getChildren().add(emptyState);
    }

    private void showEmpty() {
        detailScroll.setVisible(false);
        detailScroll.setManaged(false);
        emptyState.setVisible(true);
        emptyState.setManaged(true);
        favoriteBtn.setDisable(true);
        favoriteBtn.setText("♡");
        favoriteBtn.setSelected(false);
        favoriteBtn.getStyleClass().removeAll("favorite-btn-active");
        favoriteBtn.getStyleClass().add("favorite-btn");
    }

    public void showScheme(Scheme scheme) {
        if (scheme == null) {
            showEmpty();
            return;
        }

        // Show detail, hide empty
        emptyState.setVisible(false);
        emptyState.setManaged(false);
        detailScroll.setVisible(true);
        detailScroll.setManaged(true);
        favoriteBtn.setDisable(false);

        schemeNameLabel.setText(scheme.getName());
        categoryBadge.setText(scheme.getCategory());
        descriptionText.setText(scheme.getDescription());
        benefitValue.setText(scheme.getBenefit());
        eligibilityValue.setText(scheme.getEligibility());
        documentsValue.setText(scheme.getDocuments());
        applyLink.setText(scheme.getApplyLink());

        // Restore favorites state
        boolean isFav = favorites.contains(scheme.getName());
        favoriteBtn.setSelected(isFav);
        favoriteBtn.setText(isFav ? "♥" : "♡");
        if (isFav) {
            favoriteBtn.getStyleClass().removeAll("favorite-btn");
            favoriteBtn.getStyleClass().add("favorite-btn-active");
        } else {
            favoriteBtn.getStyleClass().removeAll("favorite-btn-active");
            favoriteBtn.getStyleClass().add("favorite-btn");
        }

        favoriteBtn.setOnAction(e -> {
            if (favoriteBtn.isSelected()) {
                favorites.add(scheme.getName());
                favoriteBtn.setText("♥");
                favoriteBtn.getStyleClass().removeAll("favorite-btn");
                favoriteBtn.getStyleClass().add("favorite-btn-active");
            } else {
                favorites.remove(scheme.getName());
                favoriteBtn.setText("♡");
                favoriteBtn.getStyleClass().removeAll("favorite-btn-active");
                favoriteBtn.getStyleClass().add("favorite-btn");
            }
        });
    }
}
