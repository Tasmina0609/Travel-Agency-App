package com.dyh.travelapp.model;

public enum TourStatus {
    ACTIVE("Active"),
    CANCELED("Canceled"),
    EXPIRED("Expired");

    private final String displayValue;

    private TourStatus(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}
