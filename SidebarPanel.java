package com.schemefinder;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;

public class SidebarPanel extends VBox {

    private NavItem activeItem;

    public SidebarPanel() {
        getStyleClass().add("sidebar");
        setPrefWidth(210);
        setMinWidth(210);
        setMaxWidth(210);

        VBox logoSection = buildLogoSection();
        VBox.setMargin(logoSection, new Insets(0, 0, 10, 0));

        // Divider
        Rectangle divider = new Rectangle();
        divider.setHeight(1);
        divider.setFill(Color.web("#FFFFFF", 0.15));
        divider.widthProperty().bind(widthProperty());

        VBox navSection = buildNavSection();
        VBox.setMargin(navSection, new Insets(16, 0, 0, 0));

        // Bottom tagline
        Label tagline = new Label("Government of India Portal");
        tagline.getStyleClass().add("sidebar-footer");
        VBox.setMargin(tagline, new Insets(20, 16, 20, 16));

        getChildren().addAll(logoSection, divider, navSection);

        // Push footer to bottom
        VBox spacer = new VBox();
        spacer.setVgrow(spacer, javafx.scene.layout.Priority.ALWAYS);
        getChildren().addAll(spacer, tagline);
    }

    private VBox buildLogoSection() {
        VBox section = new VBox(10);
        section.setAlignment(Pos.CENTER);
        section.setPadding(new Insets(28, 16, 20, 16));

        // Emblem placeholder (stylized canvas drawing)
        Canvas emblem = createEmblemCanvas();

        Label title = new Label("Scheme Finder");
        title.getStyleClass().add("sidebar-title");

        Label subtitle = new Label("Find. Check. Benefit.");
        subtitle.getStyleClass().add("sidebar-subtitle");

        section.getChildren().addAll(emblem, title, subtitle);
        return section;
    }

    private Canvas createEmblemCanvas() {
        Canvas canvas = new Canvas(72, 72);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        // Outer circle
        gc.setStroke(Color.web("#FFFFFF", 0.85));
        gc.setLineWidth(2.5);
        gc.strokeOval(4, 4, 64, 64);

        // Inner circle
        gc.setLineWidth(1.5);
        gc.strokeOval(10, 10, 52, 52);

        // Stylized Ashoka Chakra spokes
        gc.setLineWidth(1.2);
        gc.setStroke(Color.web("#FFFFFF", 0.75));
        double cx = 36, cy = 36, r1 = 14, r2 = 22;
        for (int i = 0; i < 24; i++) {
            double angle = Math.toRadians(i * 15);
            double x1 = cx + r1 * Math.cos(angle);
            double y1 = cy + r1 * Math.sin(angle);
            double x2 = cx + r2 * Math.cos(angle);
            double y2 = cy + r2 * Math.sin(angle);
            gc.strokeLine(x1, y1, x2, y2);
        }

        // Center dot
        gc.setFill(Color.web("#FFFFFF", 0.9));
        gc.fillOval(cx - 3.5, cy - 3.5, 7, 7);

        // Top pillar / crown shape
        gc.setFill(Color.web("#FFFFFF", 0.85));
        double[] xs = {29, 36, 43, 40, 32};
        double[] ys = {32, 24, 32, 30, 30};
        gc.fillPolygon(xs, ys, 5);

        return canvas;
    }

    private VBox buildNavSection() {
        VBox nav = new VBox(4);
        nav.setPadding(new Insets(0, 12, 0, 12));

        NavItem homeItem = createNavItem("🏠", "Home", true);
        NavItem searchItem = createNavItem("🔍", "Search Schemes", false);
        NavItem allItem = createNavItem("☰", "All Schemes", false);
        NavItem catItem = createNavItem("⊞", "Categories", false);

        activeItem = homeItem;

        homeItem.setOnMouseClicked(e -> setActive(homeItem));
        searchItem.setOnMouseClicked(e -> setActive(searchItem));
        allItem.setOnMouseClicked(e -> setActive(allItem));
        catItem.setOnMouseClicked(e -> setActive(catItem));

        nav.getChildren().addAll(homeItem, searchItem, allItem, catItem);
        return nav;
    }

    private NavItem createNavItem(String icon, String label, boolean active) {
        NavItem item = new NavItem(icon, label);
        if (active) {
            item.getStyleClass().add("nav-item-active");
        } else {
            item.getStyleClass().add("nav-item");
        }
        return item;
    }

    private void setActive(NavItem item) {
        if (activeItem != null) {
            activeItem.getStyleClass().removeAll("nav-item-active");
            activeItem.getStyleClass().add("nav-item");
        }
        item.getStyleClass().removeAll("nav-item");
        item.getStyleClass().add("nav-item-active");
        activeItem = item;
    }

    // Inner class for nav item
    public static class NavItem extends javafx.scene.layout.HBox {
        public NavItem(String icon, String label) {
            setAlignment(Pos.CENTER_LEFT);
            setSpacing(12);
            setPadding(new Insets(11, 16, 11, 16));
            setMaxWidth(Double.MAX_VALUE);

            Label iconLabel = new Label(icon);
            iconLabel.getStyleClass().add("nav-icon");
            iconLabel.setStyle("-fx-font-size: 15px;");

            Label textLabel = new Label(label);
            textLabel.getStyleClass().add("nav-label");

            getChildren().addAll(iconLabel, textLabel);
            getStyleClass().add("nav-item");
        }
    }
}
