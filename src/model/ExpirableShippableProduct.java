package model;

import java.time.LocalDate;
import interfaces.Expirable;
import interfaces.Shippable;

public class ExpirableShippableProduct extends Product implements Expirable, Shippable {
    private LocalDate expiryDate;
    private double weight;

    public ExpirableShippableProduct(String name, double price, int quantity, LocalDate expiryDate, double weight) {
        super(name, price, quantity);
        if (expiryDate == null) {
            throw new IllegalArgumentException("Expiry date must not be null");
        }
        if (weight <= 0) {
            throw new IllegalArgumentException("Product weight must be greater than 0");
        }
        this.expiryDate = expiryDate;
        this.weight = weight;
    }

    @Override
    public boolean isExpired() {
        return expiryDate.isBefore(LocalDate.now());
    }

    @Override
    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    @Override
    public double getWeight() {
        return weight;
    }

    @Override
    public String getName() {
        return super.getName();
    }
} 