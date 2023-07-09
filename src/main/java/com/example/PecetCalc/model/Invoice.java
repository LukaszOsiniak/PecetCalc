package com.example.PecetCalc.model;

import com.example.PecetCalc.util.Util;
import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Invoices")
@XmlRootElement(name = "Invoice")
@XmlAccessorType(XmlAccessType.FIELD)
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @XmlTransient
    Long invId;
    @XmlTransient
    String name;
    @OneToMany(cascade = CascadeType.PERSIST)
    List<Computer> computers = new ArrayList<>();
    @XmlTransient
    Double exchangeRate;
    @XmlTransient
    Double invPriceInPln;
    @XmlTransient
    Double invPriceInUsd;
    @XmlTransient
    Date invDate;

    public Long getInvId() {
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

    public Double getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(Double exchangeRate) {
        if (exchangeRate > 0) {
            this.exchangeRate = exchangeRate;
        } else {
            throw new IllegalArgumentException("Exchange rate is not valid");
        }
    }

    public Double getInvPriceInPln() {
        return invPriceInPln;
    }

    public void setInvPriceInPln(Double invPriceInPln) {
        if (invPriceInPln > 0) {
            this.invPriceInPln = invPriceInPln;
        } else {
            throw new IllegalArgumentException("Price in PLN is not valid");
        }
    }

    public Double getInvPriceInUsd() {
        return invPriceInUsd;
    }

    public void setInvPriceInUsd(Double invPriceInUsd) {
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

    public Invoice(Long invId, String name, List<Computer> computers, Double invPriceInUsd, Date invDate) {
        this.invId = invId;
        this.name = name;
        this.computers = computers;
        this.invPriceInUsd = invPriceInUsd;
        this.invDate = invDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Invoice invoice = (Invoice) o;
        return Objects.equals(invId, invoice.invId) && Objects.equals(name, invoice.name) && Objects.equals(computers, invoice.computers) && Objects.equals(exchangeRate, invoice.exchangeRate) && Objects.equals(invPriceInPln, invoice.invPriceInPln) && Objects.equals(invPriceInUsd, invoice.invPriceInUsd) && Objects.equals(invDate, invoice.invDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(invId, name, computers, exchangeRate, invPriceInPln, invPriceInUsd, invDate);
    }
}
