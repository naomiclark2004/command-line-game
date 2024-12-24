import java.util.ArrayList;

public class Player {
    private Room location;
    private int numberOfObjects;
    private ArrayList<Item> inventory;

    // player has three fields
    // location keeps track of the players current location / what room they're in
    // inventory keeps track of what items the user current has in their inventory (stored inside is the name and description of each item)
    // numberOfObjects keeps track of the current number of items in inventory

    public Player() {
        location = null;
        inventory = new ArrayList<Item>();
        numberOfObjects = 0;
    }

    public Player(Room location, int numberOfObjects) {
        this.location = location;
        this.inventory = new ArrayList<Item>();
        this.numberOfObjects = numberOfObjects;
    }

    public ArrayList<Item> take(Item item) {
        inventory.add(item);
        numberOfObjects++;

        return inventory;
    }

    public ArrayList<Item> drop(Object item) {
        inventory.remove(item);
        numberOfObjects--;

        return inventory;
    }

    public Room getLocation() {
        return location;
    }

    public void setLocation(Room location) {
        this.location = location;
    }

    public ArrayList<Item> getInventory() {
        return inventory;
    }

    public void setInventory(ArrayList<Item> inventory) {
        this.inventory = inventory;
    }

    public int getNumberOfObjects() {
        return numberOfObjects;
    }

    // viewInventory displays each item in the inventory to be displayed at anytime
    public void viewInventory() {
        for (int i = 0; i < inventory.size(); i++) {
            System.out.println(inventory.get(i));
        }

    }

}
