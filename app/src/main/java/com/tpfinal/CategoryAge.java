package com.tpfinal;

public enum CategoryAge {
    ENFANT("enfant"),
    JEUNE("jeune"),
    ADULTE("adulte"),
    SENIOR("senior"),
    INVALID("invalid");

    private final String category;

    public String getCategory() {
        return category;
    }

    CategoryAge(final String category) {
        this.category = category;
    }

    public static CategoryAge construct(int age) {
        if (age >= 0 && age <= 16) return ENFANT;
        if (age >= 17 && age <= 22) return JEUNE;
        if (age >= 23 && age <= 65) return ADULTE;
        if (age >= 65) return SENIOR;
        return INVALID;
    }
}
