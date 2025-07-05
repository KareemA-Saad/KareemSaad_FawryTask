package model;

import java.time.LocalDate;
import interfaces.Expirable;

public class ExpirableProduct extends Product implements Expirable {
    private LocalDate expiryDate;

    public ExpirableProduct(String name, double price, int quantity, LocalDate expiryDate) {
        super(name, price, quantity);
        if (expiryDate == null) {
            throw new IllegalArgumentException("Expiry date must not be null");
        }
        this.expiryDate = expiryDate;
    }

    @Override
    public boolean isExpired() {
        return expiryDate.isBefore(LocalDate.now());
    }

    @Override
    public LocalDate getExpiryDate() {
        return expiryDate;
    }
} 