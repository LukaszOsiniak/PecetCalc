package com.example.PecetCalc.model;

import com.example.PecetCalc.util.Util;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Invoice")
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long invId;
    String name;
    @OneToMany(cascade=CascadeType.PERSIST)
    List<Computer> computers = new ArrayList<>();
    double exchangeRate;
    double invPriceInPln;
    double invPriceInUsd;
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

    public double getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(double exchangeRate) {
        if (exchangeRate > 0) {
            this.exchangeRate = exchangeRate;
        } else {
            throw new IllegalArgumentException("Exchange rate is not valid");
        }
    }

    public double getInvPriceInPln() {
        return invPriceInPln;
    }

    public void setInvPriceInPln(double invPriceInPln) {
        if (invPriceInPln > 0) {
            this.invPriceInPln = invPriceInPln;
        } else {
            throw new IllegalArgumentException("Price in PLN is not valid");
        }
    }

    public double getInvPriceInUsd() {
        return invPriceInUsd;
    }

    public void setInvPriceInUsd(double invPriceInUsd) {
        if (invPriceInUsd > 0) {
            this.invPriceInUsd = invPriceInUsd;
        } else {
            throw new IllegalArgumentException("Price in USD is not valid");
        }
    }

    public Date getInvDate() {
        return invDate;
    }

    public void setInvDate(Date invDate) {
        this.invDate = invDate;
    }

    public Invoice() {
    }

    public Invoice(Long invId, String name, List<Computer> computers, double invPriceInUsd, Date invDate) {
        this.invId = invId;
        this.name = name;
        this.computers = computers;
        this.exchangeRate = Util.getRateAtDate(invDate).doubleValue();
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
