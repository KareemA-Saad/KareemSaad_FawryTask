package model;

import java.util.ArrayList;
import java.util.List;
import interfaces.Expirable;
import interfaces.Shippable;

public class Cart {
    private List<CartItem> items;
    private Customer customer;

    // Constructor
    public Cart(Customer customer) {
        if (customer == null) {
            throw new IllegalArgumentException("Customer cannot be null");
        }
        this.customer = customer;
        this.items = new ArrayList<>();
    }

    // Core Cart Operations
    //Function to add a product to the cart with handling edge cases (1) product is null, 
    //(2) quantity is less than or equal to 0, (3) quantity is greater than the available stock, 
    //(4) product already exists in the cart)//
    public void addProduct(Product product, int quantity) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than 0");
        }
        if (quantity > product.getQuantity()) {
            throw new IllegalArgumentException("Requested quantity exceeds available stock");
        }

        ////////////////////////// Check if product already exists in cart //////////////////////////
        for (CartItem item : items) {
            if (item.getProduct().getName().equals(product.getName())) {
                int newQuantity = item.getQuantity() + quantity;
                if (newQuantity > product.getQuantity()) {
                    throw new IllegalArgumentException("Total quantity exceeds available stock");
                }
                item.setQuantity(newQuantity);
                return;
            }
        }

        ////////////////////////// Add new item to cart //////////////////////////
        items.add(new CartItem(product, quantity));
    }

    public void clearCart() {
        items.clear();
    }

    // Query Methods
    public boolean isEmpty() {
        return items.isEmpty();
    }

    public List<CartItem> getItems() {
        return new ArrayList<>(items);
    }

    public Customer getCustomer() {
        return customer;
    }

    ////////////////////////// Calculation Methods //////////////////////////
    public double getSubtotal() {
        double subtotal = 0;
        for (CartItem item : items) {
            subtotal += item.getTotalPrice();
        }
        return subtotal;
    }

    public double getShippingFees() {
        // Check if cart has any shippable items
        for (CartItem item : items) {
            if (item.getProduct() instanceof Shippable) {
                return 30; // Fixed shipping fee
            }
        }
        return 0; // No shipping fee if no shippable items
    }

    public double getTotalAmount() {
        return getSubtotal() + getShippingFees();
    }

    // Validation Methods
    ////////////////////////// Validate cart with handling edge cases (1) cart is empty, 
    //(2) cart has expired products, (3) customer balance is insufficient) //////////////////////////
    public void validateCart() {
        if (isEmpty()) {
            throw new IllegalStateException("Cart is empty");
        }

        ////////////////////////// Check for expired products //////////////////////////
        for (CartItem item : items) {
            if (item.getProduct() instanceof Expirable expirable) {
                if (expirable.isExpired()) {
                    throw new IllegalStateException("Product " + item.getProduct().getName() + " is expired");
                }
            }
        }

        ////////////////////////// Check customer balance //////////////////////////
        double totalAmount = getTotalAmount();
        if (customer.getBalance() < totalAmount) {
            throw new IllegalStateException("Customer's balance is insufficient");
        }
    }

    // Utility Methods
    public List<Shippable> getShippableItems() {
        List<Shippable> shippableItems = new ArrayList<>();
        for (CartItem item : items) {
            if (item.getProduct() instanceof Shippable) {
                shippableItems.add((Shippable) item.getProduct());
            }
        }
        return shippableItems;
    }
} 