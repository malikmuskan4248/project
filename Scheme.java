package com.schemefinder;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Scheme {
    private final StringProperty name;
    private final StringProperty category;
    private final StringProperty eligibility;
    private final StringProperty description;
    private final StringProperty benefit;
    private final StringProperty documents;
    private final StringProperty applyLink;

    public Scheme(String name, String category, String eligibility,
                  String description, String benefit, String documents, String applyLink) {
        this.name = new SimpleStringProperty(name);
        this.category = new SimpleStringProperty(category);
        this.eligibility = new SimpleStringProperty(eligibility);
        this.description = new SimpleStringProperty(description);
        this.benefit = new SimpleStringProperty(benefit);
        this.documents = new SimpleStringProperty(documents);
        this.applyLink = new SimpleStringProperty(applyLink);
    }

    public String getName() { return name.get(); }
    public StringProperty nameProperty() { return name; }

    public String getCategory() { return category.get(); }
    public StringProperty categoryProperty() { return category; }

    public String getEligibility() { return eligibility.get(); }
    public StringProperty eligibilityProperty() { return eligibility; }

    public String getDescription() { return description.get(); }
    public String getBenefit() { return benefit.get(); }
    public String getDocuments() { return documents.get(); }
    public String getApplyLink() { return applyLink.get(); }
}
