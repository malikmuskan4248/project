# project
# Government Scheme Finder - JavaFX Desktop Application

A modern, professional JavaFX desktop application to help citizens discover and apply for Indian government schemes.

---

## Project Structure

```
GovernmentSchemeFinder/
├── pom.xml
└── src/
    └── main/
        ├── java/
        │   ├── module-info.java
        │   └── com/schemefinder/
        │       ├── Launcher.java           ← Entry point (fat JAR compatible)
        │       ├── MainApp.java            ← JavaFX Application class
        │       ├── MainContentPanel.java   ← Right-side content area
        │       ├── SidebarPanel.java       ← Left navigation sidebar
        │       ├── HeaderBanner.java       ← Gradient banner with building art
        │       ├── SearchPanel.java        ← Search/filter bar
        │       ├── SchemesTablePanel.java  ← Schemes table (left column)
        │       ├── SchemeDetailPanel.java  ← Scheme details (right column)
        │       ├── Scheme.java             ← Data model
        │       └── SchemeRepository.java  ← Data + search logic
        └── resources/
            └── styles/
                └── main.css               ← All UI styling
```

---

## Prerequisites

- **Java 17+** (Java 21 recommended)
- **Maven 3.6+**

Install on Ubuntu/Debian:
```bash
sudo apt install openjdk-21-jdk maven
```

Install on macOS (with Homebrew):
```bash
brew install openjdk@21 maven
```

Download for Windows: https://adoptium.net

---

## Build & Run

### Option 1: Run directly with Maven (recommended)
```bash
cd GovernmentSchemeFinder
mvn javafx:run
```

### Option 2: Build a fat JAR and run
```bash
cd GovernmentSchemeFinder
mvn clean package
java -jar target/GovernmentSchemeFinder-1.0-SNAPSHOT.jar
```

### Option 3: Run with explicit JavaFX (if module issues occur)
```bash
# Download JavaFX SDK from: https://gluonhq.com/products/javafx/
# Then:
java --module-path /path/to/javafx-sdk/lib \
     --add-modules javafx.controls,javafx.fxml,javafx.graphics \
     -cp target/GovernmentSchemeFinder-1.0-SNAPSHOT.jar \
     com.schemefinder.Launcher
```

---

## Features

| Feature | Description |
|---|---|
| **Sidebar Navigation** | Navy blue sidebar with Home, Search Schemes, All Schemes, Categories |
| **Gradient Banner** | Indigo→Teal gradient with government building illustration |
| **Search & Filter** | Live search by name, category, eligibility; filter by category dropdown |
| **12 Real Schemes** | PM-KISAN, Ayushman Bharat, Mudra Yojana, PMFBY, and more |
| **Scheme Table** | Sortable table with Scheme Name, Category badge, Eligibility |
| **Detail Panel** | Full description, benefit amount, eligibility, documents, apply link |
| **Favorites** | Heart toggle button to mark favorite schemes |
| **Responsive** | Resizable window with minimum constraints |

---

## UI Color Palette

| Role | Color |
|---|---|
| Sidebar / Navy | `#1E293B` |
| Primary / Indigo | `#4F46E5` |
| Accent / Teal | `#0D9488` |
| Highlight Orange | `#F59E0B` |
| Background | `#F1F5F9` |
| Surface White | `#FFFFFF` |

---

## Included Schemes

1. PM-KISAN (Agriculture)
2. Pradhan Mantri Fasal Bima Yojana (Agriculture)
3. Kisan Credit Card (Agriculture)
4. PM Awas Yojana Gramin (Housing)
5. Ayushman Bharat PM-JAY (Health)
6. PM Mudra Yojana (Business)
7. National Scholarship Portal (Education)
8. Sukanya Samriddhi Yojana (Women & Child)
9. PM Jan Dhan Yojana (Finance)
10. Atmanirbhar Bharat Rozgar Yojana (Employment)
11. Soil Health Card Scheme (Agriculture)
12. PM Garib Kalyan Anna Yojana (Food Security)

---

## Extending the App

To add more schemes, edit `SchemeRepository.java` and add entries to the `ALL_SCHEMES` list:

```java
new Scheme(
    "Scheme Name",
    "Category",
    "Eligibility description",
    "Full description of the scheme",
    "Benefit amount / type",
    "Required documents list",
    "Apply URL or location"
)
```

To connect a real database (e.g., SQLite), replace the static list in `SchemeRepository` with JDBC queries.
