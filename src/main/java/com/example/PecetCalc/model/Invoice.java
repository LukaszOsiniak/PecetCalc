package com.example.PecetCalc.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long invId;
    String name;
    @OneToMany
    List<Computer> computers;
    int invPriceInPln;
    int invPriceInUsd;
    Date invDate;

    public long getInvId() {
        return invId;
    }

    public void setInvId(long invId) {
        this.invId = invId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Computer> getComputers() {
        return computers;
    }

    public void setComputers(List<Computer> computers) {
        this.computers = computers;
    }

    public int getInvPriceInPln() {
        return invPriceInPln;
    }

    public void setInvPriceInPln(int invPriceInPln) {
        this.invPriceInPln = invPriceInPln;
    }

    public int getInvPriceInUsd() {
        return invPriceInUsd;
    }

    public void setInvPriceInUsd(int invPriceInUsd) {
        this.invPriceInUsd = invPriceInUsd;
    }

    public Date getInvDate() {
        return invDate;
    }

    public void setInvDate(Date invDate) {
        this.invDate = invDate;
    }

    public Invoice(long invId, String name, List<Computer> computers, int invPriceInPln, int invPriceInUsd, Date invDate) {
        this.invId = invId;
        this.name = name;
        this.computers = computers;
        this.invPriceInPln = invPriceInPln;
        this.invPriceInUsd = invPriceInUsd;
        this.invDate = invDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Invoice invoice = (Invoice) o;
        return invId == invoice.invId && invPriceInPln == invoice.invPriceInPln && invPriceInUsd == invoice.invPriceInUsd && Objects.equals(name, invoice.name) && Objects.equals(computers, invoice.computers) && Objects.equals(invDate, invoice.invDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(invId, name, computers, invPriceInPln, invPriceInUsd, invDate);
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "invId=" + invId +
                ", name='" + name + '\'' +
                ", computers=" + computers +
                ", invPriceInPln=" + invPriceInPln +
                ", invPriceInUsd=" + invPriceInUsd +
                ", invDate=" + invDate +
                '}';
    }
}
