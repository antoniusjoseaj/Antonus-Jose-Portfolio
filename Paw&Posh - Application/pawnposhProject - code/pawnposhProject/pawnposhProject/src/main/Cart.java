package main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.PetProduct;


public class Cart {
    private static ObservableList<PetProduct> items;

    public Cart() {
        this.items = FXCollections.observableArrayList();
    }

    public void addItem(PetProduct product) {
        items.add(product);
    }

    public static ObservableList<PetProduct> getItems() {
        return items;
    }

    public void clear() {
        items.clear();
    }

    public String confirmPurchase() {
        if (items.isEmpty()) {
            return "Your cart is empty. Please add items to your cart before confirming.";
        }

        StringBuilder orderSummary = new StringBuilder("Order Summary:\n");
        double totalAmount = 0;

        for (PetProduct item : items) {
            orderSummary.append(item.getName())
                        .append(" - Quantity: ")
                        .append(item.getQuantity())
                        .append(" - Price: Rp")
                        .append(item.getPrice())
                        .append("\n");
            totalAmount += item.getPrice() * item.getQuantity();
        }

        orderSummary.append("Total Amount: Rp").append(totalAmount).append("\n");
        clear(); // Clear the cart after confirming
        return orderSummary.toString();
    }

	public void removeItem(PetProduct item) {
		// TODO Auto-generated method stub
		
	}
}