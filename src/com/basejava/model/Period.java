package com.basejava.model;

import java.time.LocalDate;
import java.time.Month;
import java.util.Objects;

import static com.basejava.util.DateUtil.NOW;
import static com.basejava.util.DateUtil.of;

public class Period {
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final String title;
    private final String description;

    public Period(int startYear, Month startMonth, String title, String description) {
        this(of(startYear, startMonth), NOW, title, description);
    }

    public Period(int startYear, Month startMonth, int endYear, Month endMonth, String title, String description) {
        this(of(startYear, startMonth), of(endYear, endMonth), title, description);
    }

    public Period(LocalDate startDate, LocalDate endDate, String title, String description) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.title = title;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Period period = (Period) o;
        return title.equals(period.title) && description.equals(period.description)
                && endDate.equals(period.endDate) && startDate.equals(period.startDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, endDate, startDate);
    }

    @Override
    public String toString() {
        return "Period{" +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", endDate=" + endDate +
                ", startDate=" + startDate +
                '}';
    }
}
