import java.util.ArrayList;

public class Room {
    private String name;
    private String desc;
    private ArrayList<Item> contents;
    private int numberOfItems;

    // room has four fields
    // name stores the name of each room
    // desc stores a short description of each room
    // contents stores keeps track of what items are currently in this room
    // numberOfItems keeps track of the current number of items in the contents

    public Room(String name, String desc) {
        this.name = name;
        this.desc = desc;
        contents = new ArrayList<>();
    }

    public void addItem(Item item) {
        contents.add(item);
        numberOfItems++;
    }

    public void dropItem(Item item) {
        contents.remove(item);
        numberOfItems--;
    }

    public int getnumberOfItems() {
        numberOfItems = contents.size();
        return numberOfItems;
    }

    public ArrayList<Item> getContents() {
        return contents;
    }

    public void setContents(ArrayList<Item> contents) {
        this.contents = contents;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    // viewRoomContent displays the items in the room (both the name and description)
    public void viewRoomContent() {
        System.out.println("This was in the room: ");
        for (int i = 0; i < contents.size(); i++) {
            System.out.println(contents.get(i));
        }
    }
}
