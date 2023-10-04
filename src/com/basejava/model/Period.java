package com.basejava.model;

import java.time.LocalDate;
import java.util.Objects;

public class Period {
    private final String specialityPosition;
    private final String responsibilities;
    private final String title;
    private final String description;
    private final LocalDate endDate;
    private final LocalDate startDate;

    public Period(String specialityPosition, String responsibilities, String title,
                  String description, LocalDate endDate, LocalDate startDate) {
        this.specialityPosition = specialityPosition;
        this.responsibilities = responsibilities;
        this.title = title;
        this.description = description;
        this.endDate = endDate;
        this.startDate = startDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Period period = (Period) o;
        return specialityPosition.equals(period.specialityPosition) && responsibilities.equals(period.responsibilities)
                && title.equals(period.title) && description.equals(period.description)
                && endDate.equals(period.endDate) && startDate.equals(period.startDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(specialityPosition, responsibilities, title, description, endDate, startDate);
    }

    @Override
    public String toString() {
        return "Period{" +
                "specialityPosition='" + specialityPosition + '\'' +
                ", responsibilities='" + responsibilities + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", endDate=" + endDate +
                ", startDate=" + startDate +
                '}';
    }
}
