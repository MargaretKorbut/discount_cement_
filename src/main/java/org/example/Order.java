package org.example;

import java.time.LocalDateTime;

public class Order {

    private final LocalDateTime dateTime;
    private final String companyName;
    private final double kg;

    public Order(LocalDateTime dateTime, String companyName, double kg) {
        this.dateTime = dateTime;
        this.companyName = companyName;
        this.kg = kg;
    }

    public LocalDateTime getDateTime(){
        return dateTime;
    }

    public String getCompanyName(){
        return companyName;
    }

    public double getKg(){
        return kg;
    }

}
