# E-Commerce System

A simple Java-based e-commerce system that demonstrates object-oriented programming principles and handles various product types, cart operations, and checkout processes.

## Features

- **Product Management**
**Support for different product types with validation**
  - Basic products (Mobile scratch cards)
  - Expirable products (Cheese, Biscuits)
  - Shippable products (TV)
  - Combined expirable and shippable products

- **Shopping Cart**: Add products with quantity validation and stock checking
- **Checkout Process**: Complete checkout with payment processing and receipt generation
- **Shipping support**: Automatic shipping handling for physical products
- **Edge Case Handling**: Comprehensive validation for all business scenarios

## Project Structure

```
src/
├── interfaces/          # All interfaces
│   ├── Expirable.java
│   └── Shippable.java
├── model/              # Implementation classes
│   ├── Product.java
│   ├── ExpirableProduct.java
│   ├── ShippableProduct.java
│   ├── ExpirableShippableProduct.java
│   ├── Customer.java
│   ├── Cart.java
│   └── CartItem.java
└── service/
    ├── CheckoutService.java
    └── ShippingService.java
```

## How to Run

### Prerequisites
- Java 8 or higher
- Any Java IDE or command line

### Running the Application

1. **Clone or download the project**
2. **Compile the Java files**:
   ```bash
   javac -d . src/*.java src/model/*.java src/service/*.java
   ```
3. **Run the main application**:
   ```bash
   java Main
   ```
4. **Run edge case tests** (optional):
   ```bash
   java TestEdgeCases
   ```

## Example Usage

The system demonstrates a complete e-commerce workflow:

```java
// Create products
ExpirableShippableProduct cheese = new ExpirableShippableProduct("Cheese", 100, 10, expiryDate, 0.4);
ShippableProduct tv = new ShippableProduct("TV", 200, 5, 1.5);
Product scratchCard = new Product("Mobile scratch card", 50, 20);

// Create customer and cart
Customer customer = new Customer("John Doe", 1000);
Cart cart = new Cart(customer);

// Add products to cart
cart.addProduct(cheese, 2);
cart.addProduct(tv, 3);
cart.addProduct(scratchCard, 1);

// Checkout
CheckoutService checkoutService = new CheckoutService(null);
checkoutService.checkout(customer, cart);
```

## Sample Output

```
** Shipment notice **
2x Cheese        400g
1x Biscuits      700g
Total package weight 1.1kg

** Checkout receipt **
2x Cheese        200
1x Biscuits      150
--------------------
Subtotal         350
Shipping         30
Amount           380
Customer balance after payment: 620
```

## Edge Cases Handled

- **Product Validation**: Null/empty names, negative prices, invalid quantities
- **Cart Operations**: Stock validation, duplicate product handling
- **Checkout Process**: Empty cart, insufficient balance, expired products
- **Customer Management**: Invalid balance operations
- **Shipping**: Weight validation, shipping fee calculation

## Design Principles

- **SOLID Principles**: Clean separation of concerns and responsibilities
- **Interface Segregation**: Separate interfaces for expirable and shippable products
- **Validation**: Comprehensive input validation with meaningful error messages
- **Fail-Fast**: Early detection and clear reporting of invalid operations

## Contributing

This project demonstrates core OOP concepts and can be extended with:
- Database integration
- Web interface
- Payment gateway integration
- Inventory management
- Order tracking

## License

This project is created for educational purposes and demonstrates Java OOP principles. 