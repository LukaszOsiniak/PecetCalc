package com.example.PecetCalc.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "Computers")
public class Computer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long cpuId;
    String name;
    double priceInUSD;
    double priceInPln;
    double exchangeRate;
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

    public double getPriceInUSD() {
        return priceInUSD;
    }

    public void setPriceInUSD(double priceInUSD) {
        if (priceInUSD > 0) {
            this.priceInUSD = priceInUSD;
        } else {
            throw new IllegalArgumentException("Price in USD is not valid");
        }
    }

    public double getPriceInPln() {
        return priceInPln;
    }

    public void setPriceInPln(double priceInPln) {
        if (priceInPln > 0) {
            this.priceInPln = priceInPln;
        } else {
            throw new IllegalArgumentException("Price in PLN is not valid");
        }
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
    public Computer(ComputerBuilder builder) {
        this.cpuId = builder.cpuId;
        this.name = builder.name;
        this.priceInUSD = builder.priceInUSD;
        this.priceInPln = builder.priceInPln;
        //this.invoice = builder.invoice;
    }

    public static class ComputerBuilder {
        private Long cpuId;
        private String name;
        private int priceInUSD;
        private int priceInPln;;
        //private Invoice invoice;

        public ComputerBuilder cpuId(Long cpuId) {
            this.cpuId = cpuId;
            return this;
        }

        public ComputerBuilder name(String name) {
            this.name = name;
            return this;
        }

        public ComputerBuilder priceInUSD(int priceInUSD) {
            if (priceInUSD > 0) {
                this.priceInUSD = priceInUSD;
                return this;
            } else {
                throw new IllegalArgumentException("Price in USD is not valid");
            }
        }

        public ComputerBuilder priceInPln(int priceInPln) {
            if (priceInPln > 0) {
                this.priceInPln = priceInPln;
                return this;
            } else {
                throw new IllegalArgumentException("Price in PLN is not valid");
            }
        }

//        public ComputerBuilder invoice(Invoice invoice) {
//            this.invoice = invoice;
//            return this;
//        }

        public Computer build() {
            return new Computer(this);
        }
    }

    @Override
    public String toString() {
        return "Computer{" +
                "cpuId=" + cpuId +
                ", name='" + name + '\'' +
                ", priceInUSD=" + priceInUSD +
                ", priceInPln=" + priceInPln +
                ", exchangeRate=" + exchangeRate +
                ", invoice=" + invoice +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Computer computer = (Computer) o;
        return priceInUSD == computer.priceInUSD && priceInPln == computer.priceInPln && exchangeRate == computer.exchangeRate && Objects.equals(cpuId, computer.cpuId) && Objects.equals(name, computer.name) && Objects.equals(invoice, computer.invoice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpuId, name, priceInUSD, priceInPln, exchangeRate, invoice);
    }
}
