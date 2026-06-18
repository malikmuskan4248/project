package com.schemefinder;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SchemeRepository {

    private static final ObservableList<Scheme> ALL_SCHEMES = FXCollections.observableArrayList(
        new Scheme(
            "PM-KISAN (Pradhan Mantri Kisan Samman Nidhi)",
            "Agriculture",
            "Small & Marginal Farmers",
            "Provides income support of ₹6,000 per year to all land holding farmer families in 3 equal installments of ₹2,000 each.",
            "₹6,000 per year (₹2,000 per installment, 3 installments)",
            "Aadhaar Card, Land Records (Khasra/Khatauni), Bank Passbook",
            "https://pmkisan.gov.in"
        ),
        new Scheme(
            "Pradhan Mantri Fasal Bima Yojana",
            "Agriculture",
            "All Farmers (Loanee & Non-Loanee)",
            "Provides comprehensive insurance coverage against failure of crops due to non-preventable natural risks.",
            "Insurance coverage for crop loss. Premium: 2% for Kharif, 1.5% for Rabi crops",
            "Land Records, Aadhaar, Bank Account, Sowing Certificate",
            "https://pmfby.gov.in"
        ),
        new Scheme(
            "Kisan Credit Card (KCC)",
            "Agriculture",
            "Farmers, Fishermen, Animal Husbandry",
            "Provides adequate and timely credit to farmers for their agricultural operations at subsidized interest rates.",
            "Short-term credit up to ₹3 lakh at 4% interest rate per annum",
            "Identity Proof, Land Records, Passport Size Photo",
            "https://pmkisan.gov.in/kcc"
        ),
        new Scheme(
            "PM Awas Yojana (Gramin)",
            "Housing",
            "Rural BPL Households",
            "Aims to provide housing for all in rural areas by constructing pucca houses with basic amenities for homeless families.",
            "₹1.2 lakh in plain areas; ₹1.3 lakh in hilly/difficult areas",
            "Aadhaar, SECC Data, BPL Certificate, Bank Account",
            "https://pmayg.nic.in"
        ),
        new Scheme(
            "Ayushman Bharat PM-JAY",
            "Health",
            "Bottom 40% Economic Population",
            "World's largest health insurance scheme providing coverage for secondary and tertiary hospitalization.",
            "₹5 lakh per family per year for medical treatment",
            "Aadhaar Card, Ration Card, Income Certificate",
            "https://pmjay.gov.in"
        ),
        new Scheme(
            "PM Mudra Yojana",
            "Business",
            "Non-Corporate Small Business Sector",
            "Provides loans up to ₹10 lakh to non-corporate, non-farm small/micro enterprises.",
            "Shishu: up to ₹50K; Kishore: ₹50K–5L; Tarun: ₹5L–10L",
            "Identity Proof, Address Proof, Business Plan, Bank Statement",
            "https://mudra.org.in"
        ),
        new Scheme(
            "National Scholarship Portal (NSP)",
            "Education",
            "Students from Economically Weaker Sections",
            "One-stop platform for all scholarship schemes. Includes pre-matric, post-matric and merit-cum-means scholarships.",
            "Varies by scheme: ₹1,000 – ₹20,000 per annum",
            "Income Certificate, Marksheets, Aadhaar, Bank Account",
            "https://scholarships.gov.in"
        ),
        new Scheme(
            "Sukanya Samriddhi Yojana",
            "Women & Child",
            "Girl Child (Below 10 years)",
            "Small deposit savings scheme for a girl child, encouraging parents to build a fund for their daughter's future education and marriage.",
            "7.6% interest per annum (tax-free). Minimum ₹250/year deposit",
            "Birth Certificate (Girl Child), Parents' ID & Address Proof",
            "Post Office or Authorized Bank Branch"
        ),
        new Scheme(
            "Pradhan Mantri Jan Dhan Yojana",
            "Finance",
            "All Indian Citizens (Unbanked)",
            "Financial inclusion initiative for universal banking access, RuPay debit card, overdraft facility and insurance.",
            "Zero-balance account, RuPay card, ₹2L accident insurance, ₹30K life cover",
            "Aadhaar Card or any one OVD (Officially Valid Document)",
            "Any bank branch or Business Correspondent"
        ),
        new Scheme(
            "Atmanirbhar Bharat Rozgar Yojana",
            "Employment",
            "New Employees (Salary < ₹15K/month)",
            "Incentivizes employers to hire new employees and restore lost jobs due to COVID-19 by subsidizing EPF contributions.",
            "Govt pays 24% EPF contribution (12% each for employee & employer) for 2 years",
            "EPFO Registration, Aadhaar, Bank Account",
            "https://epfindia.gov.in"
        ),
        new Scheme(
            "Soil Health Card Scheme",
            "Agriculture",
            "All Farmers",
            "Issues soil health cards to farmers containing crop-wise recommendations of nutrients and fertilizers for individual farms.",
            "Free soil testing and personalized fertilizer recommendations",
            "Land Records, Aadhaar Card, Mobile Number",
            "Nearest Krishi Vigyan Kendra or Agriculture Dept Office"
        ),
        new Scheme(
            "PM Garib Kalyan Anna Yojana",
            "Food Security",
            "NFSA Beneficiaries (Ration Card Holders)",
            "Provides 5 kg free foodgrains per person per month to beneficiaries under the National Food Security Act.",
            "5 kg free rice/wheat per person per month",
            "Ration Card (AAY/PHH), Aadhaar",
            "Nearest Public Distribution System (PDS) Shop"
        )
    );

    public static ObservableList<Scheme> getAllSchemes() {
        return ALL_SCHEMES;
    }

    public static List<String> getCategories() {
        List<String> categories = ALL_SCHEMES.stream()
            .map(Scheme::getCategory)
            .distinct()
            .sorted()
            .collect(Collectors.toList());
        return categories;
    }

    public static ObservableList<Scheme> search(String query, String category) {
        String q = (query == null) ? "" : query.trim().toLowerCase();
        String cat = (category == null || category.equals("All Categories")) ? "" : category;

        return ALL_SCHEMES.filtered(scheme -> {
            boolean matchesQuery = q.isEmpty()
                || scheme.getName().toLowerCase().contains(q)
                || scheme.getCategory().toLowerCase().contains(q)
                || scheme.getEligibility().toLowerCase().contains(q)
                || scheme.getDescription().toLowerCase().contains(q);

            boolean matchesCategory = cat.isEmpty()
                || scheme.getCategory().equalsIgnoreCase(cat);

            return matchesQuery && matchesCategory;
        });
    }
}
