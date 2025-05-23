package com.example.demo.models;

import java.io.Serializable;

public class Payment implements Serializable {
    //for jackson
    private static final long serialVersionUID = 1L;

    //class eke attributes tika
    private int paymentId;
    private double paymentAmount;

    // default constructor
    //object ekak hadaddi deault values walin object ekak hadanwa
    public Payment() {
    }

    //parameterized constructor
    //object ekak hadaddi custom values aragena object ekak hadanwa
    public Payment(int paymentId, double paymentAmount) {
        //payment id eka negative wunoth error ekak danwa
        if (paymentId <= 0) {
            throw new IllegalArgumentException("Payment ID must be greater than 0");
        }

        //payment amount eka 1300 adu wunoth error ekak danwa
        if (paymentAmount < 1300) {
            throw new IllegalArgumentException("Payment amount must be at least 1300");
        }

        this.paymentId = paymentId;
        this.paymentAmount = paymentAmount;
    }

    //getters
    public int getPaymentId() {
        return paymentId;
    }

    public double getPaymentAmount() {
        return paymentAmount;
    }

    // setters
    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public void setPaymentAmount(double paymentAmount) {
        //payment amount eka 1300 wada adu wunoth error ekak danwa
        if (paymentAmount < 1300) {
            throw new IllegalArgumentException("Payment amount must be at least 1300.");
        }
        this.paymentAmount = paymentAmount;
    }
}
