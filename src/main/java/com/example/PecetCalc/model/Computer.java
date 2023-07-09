package com.example.PecetCalc.model;

import jakarta.persistence.*;

import jakarta.xml.bind.annotation.*;

import java.util.Objects;

@Entity
@Table(name = "Computers")
@XmlRootElement(name = "Computer")
@XmlAccessorType(XmlAccessType.FIELD)
public class Computer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @XmlTransient
    Long cpuId;
    String name;
    Double priceInUSD;
    Double priceInPln;
    Double exchangeRate;
    @ManyToOne
    Invoice invoice;

    public Long getCpuId() {
        return cpuId;
    }

    public void setCpuId(Long cpuId) {
        this.cpuId = cpuId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPriceInUSD() {
        return priceInUSD;
    }

    public Double getPriceInPln() {
        return priceInPln;
    }

    public Double getExchangeRate() {
        return exchangeRate;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public void setPriceInUSD(Double priceInUSD) {
        if (priceInUSD > 0) {
            this.priceInUSD = priceInUSD;
        } else {
            throw new IllegalArgumentException("Price in USD is not valid");
        }
    }

    public void setPriceInPln(Double priceInPln) {
        if (priceInPln > 0) {
            this.priceInPln = priceInPln;
        } else {
            throw new IllegalArgumentException("Price in PLN is not valid");
        }
    }

    public void setExchangeRate(Double exchangeRate) {
        if (exchangeRate > 0) {
            this.exchangeRate = exchangeRate;
        } else {
            throw new IllegalArgumentException("Exchange rate is not valid");
        }
    }

    public Computer() {
    }

    public Computer(Long cpuId, String name, Double priceInUSD) {
        this.cpuId = cpuId;
        this.name = name;
        this.priceInUSD = priceInUSD;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Computer computer = (Computer) o;
        return Objects.equals(cpuId, computer.cpuId) && Objects.equals(name, computer.name) && Objects.equals(priceInUSD, computer.priceInUSD) && Objects.equals(priceInPln, computer.priceInPln) && Objects.equals(exchangeRate, computer.exchangeRate) && Objects.equals(invoice, computer.invoice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpuId, name, priceInUSD, priceInPln, exchangeRate, invoice);
    }
}
