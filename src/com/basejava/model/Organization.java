package com.basejava.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class Organization implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final Organization EMPTY = new Organization("", "", Period.EMPTY);
    private Link website;
    private List<Period> periods = new ArrayList<>();

    public Organization() {
    }

    public Organization(String name, String url, Period... periods) {
        this(new Link(name, url), Arrays.asList(periods));
    }


    public Organization(Link website, List<Period> periods) {
        this.website = website;
        this.periods = periods;
    }

    public Link getWebsite() {
        return website;
    }

    public List<Period> getPeriods() {
        return periods;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return Objects.equals(website, that.website) && Objects.equals(periods, that.periods);
    }

    @Override
    public int hashCode() {
        return Objects.hash(website, periods);
    }

    @Override
    public String toString() {
        return "Organization{" +
                "website='" + website + '\'' +
                ", periods=" + periods +
                '}';
    }
}
