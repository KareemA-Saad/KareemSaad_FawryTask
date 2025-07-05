import java.time.LocalDate;
import model.*;
import service.*;

public class Main {
    public static void main(String[] args) {
        // Create products as per requirements
        ExpirableShippableProduct cheese = new ExpirableShippableProduct("Cheese", 100, 10, LocalDate.now().plusDays(30), 0.4);
        ShippableProduct tv = new ShippableProduct("TV", 200, 5, 1.5);
        Product scratchCard = new Product("Mobile scratch card", 50, 20);
        ExpirableProduct Biscuts = new ExpirableProduct("Biscuits", 150, 8, LocalDate.now().plusDays(15));
        
        // Create customer
        Customer customer = new Customer("John Doe", 1000);
        
        // Create cart
        Cart cart = new Cart(customer);
        
        // Add products to cart as per example
        cart.addProduct(cheese, 2);
        cart.addProduct(tv, 3);
        cart.addProduct(scratchCard, 1);
        
        // Create checkout service and perform checkout
        CheckoutService checkoutService = new CheckoutService(null);
        checkoutService.checkout(customer, cart);
        
       
}
}
