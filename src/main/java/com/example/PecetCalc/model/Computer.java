package com.example.PecetCalc.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Date;
import java.util.Objects;

public class Computer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long cpuId;
    String name;
    int inDollars;
    int inPln;
    int exchangeRate;
    Date thirdJanuaryExchangeRate;
    Date tenthJanuaryExchangeRate;

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

    public int getInDollars() {
        return inDollars;
    }

    public void setInDollars(int inDollars) {
        this.inDollars = inDollars;
    }

    public int getInPln() {
        return inPln;
    }

    public void setInPln(int inPln) {
        this.inPln = inPln;
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

    public Computer(long cpuId, String name, int inDollars, int inPln, int exchangeRate, Date thirdJanuaryExchangeRate, Date tenthJanuaryExchangeRate) {
        this.cpuId = cpuId;
        this.name = name;
        this.inDollars = inDollars;
        this.inPln = inPln;
        this.exchangeRate = exchangeRate;
        this.thirdJanuaryExchangeRate = thirdJanuaryExchangeRate;
        this.tenthJanuaryExchangeRate = tenthJanuaryExchangeRate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Computer computer = (Computer) o;
        return cpuId == computer.cpuId && inDollars == computer.inDollars && inPln == computer.inPln && exchangeRate == computer.exchangeRate && Objects.equals(name, computer.name) && Objects.equals(thirdJanuaryExchangeRate, computer.thirdJanuaryExchangeRate) && Objects.equals(tenthJanuaryExchangeRate, computer.tenthJanuaryExchangeRate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpuId, name, inDollars, inPln, exchangeRate, thirdJanuaryExchangeRate, tenthJanuaryExchangeRate);
    }

    @Override
    public String toString() {
        return "Computer{" +
                "cpuId=" + cpuId +
                ", name='" + name + '\'' +
                ", inDollars=" + inDollars +
                ", inPln=" + inPln +
                ", exchangeRate=" + exchangeRate +
                ", thirdJanuaryExchangeRate=" + thirdJanuaryExchangeRate +
                ", tenthJanuaryExchangeRate=" + tenthJanuaryExchangeRate +
                '}';
    }
}
