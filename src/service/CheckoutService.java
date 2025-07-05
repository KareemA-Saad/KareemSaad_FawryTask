package service;

import model.*;
import interfaces.Shippable;
import java.util.List;

public class CheckoutService {
    private ShippingService shippingService;

    // Constructor
    public CheckoutService(ShippingService shippingService) {
        this.shippingService = shippingService;
    }

    // Main Checkout Operation
    public void checkout(Customer customer, Cart cart) {
        try {
            // Validate cart before checkout
            validateCheckout(cart);
            
            // Calculate amounts
            double subtotal = cart.getSubtotal();
            double shippingFees = cart.getShippingFees();
            double totalAmount = cart.getTotalAmount();
            
            // Process payment
            processPayment(customer, totalAmount);
            
            // Handle shipping if needed
            handleShipping(cart);
            
            // Print checkout receipt
            printCheckoutReceipt(cart, subtotal, shippingFees, totalAmount, customer.getBalance());
            
            // Clear cart after successful checkout
            cart.clearCart();
            
        } catch (Exception e) {
            System.out.println("Checkout failed: " + e.getMessage());
            throw e;
        }
    }

    // Validation Methods
    ////////////////////////// Validate checkout with all edge cases //////////////////////////
    private void validateCheckout(Cart cart) {
        // Use cart's validation method
        cart.validateCart();
        
        // Additional checkout-specific validations can be added here
    }

    // Processing Methods
    ////////////////////////// Process payment and deduct from customer balance //////////////////////////
    private void processPayment(Customer customer, double amount) {
        customer.deductBalance(amount);
    }

    ////////////////////////// Handle shipping for shippable items //////////////////////////
    private void handleShipping(Cart cart) {
        List<Shippable> shippableItems = cart.getShippableItems();
        
        if (!shippableItems.isEmpty()) {
            // Print shipment notice
            printShipmentNotice(shippableItems);
            
            // Send to shipping service
            if (shippingService != null) {
                shippingService.ship(shippableItems);
            }
        }
    }

    // Display Methods
    ////////////////////////// Print shipment notice with item details //////////////////////////
    private void printShipmentNotice(List<Shippable> shippableItems) {
        System.out.println("** Shipment notice **");
        
        double totalWeight = 0;
        for (Shippable item : shippableItems) {
            System.out.println("1x " + item.getName() + "\t\t" + (item.getWeight() * 1000) + "g");
            totalWeight += item.getWeight();
        }
        
        System.out.println("Total package weight " + totalWeight + "kg");
        System.out.println();
    }

    ////////////////////////// Print checkout receipt with all details //////////////////////////
    private void printCheckoutReceipt(Cart cart, double subtotal, double shippingFees, double totalAmount, double remainingBalance) {
        System.out.println("** Checkout receipt **");
        
        // Print cart items
        for (CartItem item : cart.getItems()) {
            System.out.println(item.getQuantity() + "x " + item.getProduct().getName() + "\t\t" + (int)item.getTotalPrice());
        }
        
        System.out.println("--------------------");
        System.out.println("Subtotal\t\t" + (int)subtotal);
        System.out.println("Shipping\t\t" + (int)shippingFees);
        System.out.println("Amount\t\t\t" + (int)totalAmount);
        System.out.println("Customer balance after payment: " + (int)remainingBalance);
    }
} 