package service;

import interfaces.Shippable;
import java.util.List;

public interface ShippingService {
    void ship(List<Shippable> shippableItems);
} 