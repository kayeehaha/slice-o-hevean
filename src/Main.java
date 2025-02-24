import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class SliceOHeaven {
    private String storeName;
    private String storeAddress;
    private String storeEmail;
    private String storePhone;
    private Map<String, List<String>> storeMenu;
    private Map<String, List<String>> pizzaIngredients;
    private Map<String, Double> pizzaPrice;
    private Map<String, Double> sides;
    private Map<String, Double> drinks;
    private String orderID;
    private double orderTotal;
    private List<String> orderItems;

    public SliceOHeaven(String storeName, String storeAddress, String storeEmail, String storePhone) {
        this.storeName = storeName;
        this.storeAddress = storeAddress;
        this.storeEmail = storeEmail;
        this.storePhone = storePhone;
        initializeMenu();
        this.orderID = null;
        this.orderTotal = 0;
        this.orderItems = new ArrayList<>();
    }

    private void initializeMenu() {
        storeMenu = new HashMap<>();
        pizzaIngredients = new HashMap<>();
        pizzaPrice = new HashMap<>();
        sides = new HashMap<>();
        drinks = new HashMap<>();
        List<String> pizzas = new ArrayList<>();
        pizzas.add("Margherita");
        pizzas.add("Pepperoni");
        pizzas.add("BBQ Chicken");
        pizzas.add("Vegetarian");
        storeMenu.put("Pizza", pizzas);
        pizzaIngredients.put("Margherita", List.of("Tomato", "Cheese", "Basil"));
        pizzaIngredients.put("Pepperoni", List.of("Tomato", "Cheese", "Pepperoni"));
        pizzaIngredients.put("BBQ Chicken", List.of("Chicken", "BBQ Sauce", "Cheese"));
        pizzaIngredients.put("Vegetarian", List.of("Tomato", "Cheese", "Olives", "Peppers", "Onions"));
        pizzaPrice.put("Margherita", 8.99);
        pizzaPrice.put("Pepperoni", 9.99);
        pizzaPrice.put("BBQ Chicken", 10.99);
        pizzaPrice.put("Vegetarian", 9.49);
        List<String> sideItems = new ArrayList<>();
        sideItems.add("Garlic Bread");
        sideItems.add("French Fries");
        sideItems.add("Salad");
        storeMenu.put("Sides", sideItems);
        sides.put("Garlic Bread", 3.99);
        sides.put("French Fries", 2.49);
        sides.put("Salad", 4.99);
        List<String> drinkItems = new ArrayList<>();
        drinkItems.add("Coke");
        drinkItems.add("Pepsi");
        drinkItems.add("Water");
        storeMenu.put("Drinks", drinkItems);
        drinks.put("Coke", 1.99);
        drinks.put("Pepsi", 1.99);
        drinks.put("Water", 0.99);
    }

    public void takeOrder() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to " + storeName);
        System.out.println("Address: " + storeAddress);
        System.out.println("Menu:");
        for (Map.Entry<String, List<String>> entry : storeMenu.entrySet()) {
            System.out.println("\n" + entry.getKey() + ":");
            for (String item : entry.getValue()) {
                System.out.println("- " + item);
            }
        }

        System.out.print("\nPlease enter your order ID: ");
        this.orderID = scanner.nextLine();
        processOrder("Pizza", pizzaPrice, scanner);
        processOrder("Sides", sides, scanner);
        processOrder("Drinks", drinks, scanner);
    }

    private void processOrder(String category, Map<String, Double> itemPrices, Scanner scanner) {
        while (true) {
            System.out.print("\nChoose a " + category.toLowerCase() + " (" + String.join(", ", storeMenu.get(category)) + ") or type 'done' to finish: ");
            String choice = scanner.nextLine();
            if ("done".equalsIgnoreCase(choice)) {
                break;
            }
            if (itemPrices.containsKey(choice)) {
                orderItems.add(choice);
                orderTotal += itemPrices.get(choice);
                System.out.println(choice + " added to order. Price: $" + itemPrices.get(choice));
            } else {
                System.out.println("Invalid " + category.toLowerCase() + " choice! Please try again.");
            }
        }
    }

    public void makePizza() {
        if (orderItems.isEmpty()) {
            System.out.println("No order placed.");
            return;
        }
        for (String item : orderItems) {
            if (pizzaIngredients.containsKey(item)) {
                System.out.println("\nMaking your " + item + " pizza...");
                System.out.println("Ingredients: " + String.join(", ", pizzaIngredients.get(item)));
            }
        }
    }

    public void printReceipt() {
        if (orderItems.isEmpty()) {
            System.out.println("No order placed.");
            return;
        }
        System.out.println("\n--- Receipt ---");
        System.out.println("Order ID: " + orderID);
        System.out.println("Items Ordered:");
        for (String item : orderItems) {
            System.out.println("- " + item);
        }
        System.out.printf("Total: $%.2f\n", orderTotal);
        System.out.println("\nThank you for ordering from " + storeName + "!");
        System.out.println("Store Address: " + storeAddress);
        System.out.println("Store Phone: " + storePhone);
        System.out.println("Store Email: " + storeEmail);
    }
}

public class Main {
    public static void main(String[] args) {
        SliceOHeaven pizzeria = new SliceOHeaven("Slice - o - Heaven", "123 Pizza St", "contact@sliceoheaven.com", "123 - 456 - 7890");
        pizzeria.takeOrder();
        pizzeria.makePizza();
        pizzeria.printReceipt();
    }
}