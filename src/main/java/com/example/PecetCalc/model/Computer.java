package com.example.PecetCalc.model;

import jakarta.persistence.*;


import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "Computers")
public class Computer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    Long cpuId;
    String name;
    int priceInUSD;
    int priceInPln;
    int exchangeRate;
    Date thirdJanuaryExchangeRate;
    Date tenthJanuaryExchangeRate;
    @ManyToOne
    Invoice invoice;

    public long getCpuId() {
        return cpuId;
    }

    public void setCpuId(long cpuId) {
        this.cpuId = cpuId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPriceInUSD() {
        return priceInUSD;
    }

    public void setPriceInUSD(int priceInUSD) {
        this.priceInUSD = priceInUSD;
    }

    public int getPriceInPln() {
        return priceInPln;
    }

    public void setPriceInPln(int priceInPln) {
        this.priceInPln = priceInPln;
    }

    public int getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(int exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public Date getThirdJanuaryExchangeRate() {
        return thirdJanuaryExchangeRate;
    }

    public void setThirdJanuaryExchangeRate(Date thirdJanuaryExchangeRate) {
        this.thirdJanuaryExchangeRate = thirdJanuaryExchangeRate;
    }

    public Date getTenthJanuaryExchangeRate() {
        return tenthJanuaryExchangeRate;
    }

    public void setTenthJanuaryExchangeRate(Date tenthJanuaryExchangeRate) {
        this.tenthJanuaryExchangeRate = tenthJanuaryExchangeRate;
    }

    public void setCpuId(Long cpuId) {
        this.cpuId = cpuId;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }
    public Computer() {
    }

    public Computer(Long cpuId, String name, int priceInUSD) {
        this.cpuId = cpuId;
        this.name = name;
        this.priceInUSD = priceInUSD;
    }

    @Override
    public String toString() {
        return "Computer{" +
                "cpuId=" + cpuId +
                ", name='" + name + '\'' +
                ", priceInUSD=" + priceInUSD +
                ", priceInPln=" + priceInPln +
                ", exchangeRate=" + exchangeRate +
                ", thirdJanuaryExchangeRate=" + thirdJanuaryExchangeRate +
                ", tenthJanuaryExchangeRate=" + tenthJanuaryExchangeRate +
                ", invoice=" + invoice +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Computer computer = (Computer) o;
        return priceInUSD == computer.priceInUSD && priceInPln == computer.priceInPln && exchangeRate == computer.exchangeRate && Objects.equals(cpuId, computer.cpuId) && Objects.equals(name, computer.name) && Objects.equals(thirdJanuaryExchangeRate, computer.thirdJanuaryExchangeRate) && Objects.equals(tenthJanuaryExchangeRate, computer.tenthJanuaryExchangeRate) && Objects.equals(invoice, computer.invoice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpuId, name, priceInUSD, priceInPln, exchangeRate, thirdJanuaryExchangeRate, tenthJanuaryExchangeRate, invoice);
    }
}
