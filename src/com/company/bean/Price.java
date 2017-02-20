package com.company.bean;

import java.io.Serializable;

/**
 * Created by PC on 18.02.2017.
 */
public class Price implements Serializable {
    private double priceFirst;
    private double priceSecond;
    private double priceThird;

    public Price() {

    }

    public double getPriceThird() {
        return priceThird;
    }

    public void setPriceThird(double priceThird) {
        this.priceThird = priceThird;
    }

    public double getPriceSecond() {
        return priceSecond;
    }

    public void setPriceSecond(double priceSecond) {
        this.priceSecond = priceSecond;
    }

    public double getPriceFirst() {
        return priceFirst;
    }

    public void setPriceFirst(double priceFirst) {
        this.priceFirst = priceFirst;
    }

    @Override
    public String toString() {
        if(priceSecond==0 & priceThird == 0) {
            return "{" +
                    "priceFirst=" + priceFirst+'}';
        }else {
            if (priceThird == 0) {
                return "{" +
                        "priceFirst=" + priceFirst +
                        ", priceSecond=" + priceSecond + '}';
            } else {
                return "{" +
                        "priceFirst=" + priceFirst +
                        ", priceSecond=" + priceSecond +
                        ", PriceThird=" + priceThird +
                        '}';
            }
        }
    }
}

