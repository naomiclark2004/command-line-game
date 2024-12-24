import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    Room rooms[];
    Item item[];
    Player player;

    public void showMenu() {
        System.out.println("Choose command: ");
        System.out.println("g - To go to given room.");
        System.out.println("l - To see what items are in the room.");
        System.out.println("t - To put given item in inventory, to be used later.");
        System.out.println("d - To drop item into current room and out of inventory.");
        System.out.println("u - To use given item from inventory.");
        System.out.println("vm - To view the list of available rooms.");
        System.out.println("vi - To view inventory.");
        System.out.println("e - To quit game.");
    }

    public void init() {
        rooms = new Room[4]; // 4 rooms
        item = new Item[2]; // 2 items

        item[0] = new Item("Suit", "You need this to survive outer space.");
        item[1] = new Item("Key", "You need this to open airlock door.");

        rooms[0] = new Room("Control Room", "This is your starting point.");
        rooms[1] = new Room("Bedroom", "This room has your clothes in it.");
        rooms[2] = new Room("Office", "This is your office room.");
        rooms[3] = new Room("Airlock Room", "This room has a door to leave the spaceship from.");

        rooms[1].addItem(item[0]);
        rooms[2].addItem(item[1]);

        // initiate player in control room
        player = new Player(rooms[0], 0);

    }

    public void startGame() {
        init();
        // Game premise
        System.out.println(
                "WELCOME\nYou're the sole passenger of the starship USS Genesis. You must abandon ship you are headed for a black hole! To safely escape you must escape through the air lock, but first you must find the key to the Airlock door. You've missplaced it somewhere on the ship. Once you've found it GO to the airlock room and USE the key. You have TWO MINUTES Good Luck!");

        // allow user input
        try (Scanner input = new Scanner(System.in)) {
            boolean run = true;
            boolean suitOn = false;
            Room currentLoc = player.getLocation();
            showMenu();
            System.out.println("Current Location: " + currentLoc.getName());
            do {
                String cmd = input.next();
                if (cmd.equalsIgnoreCase("g")) {
                    // when user enters g prompt for room number and check if vaild room number 
                    System.out.print("What room number do you want to do to? ");
                    int here = input.nextInt();
                    if (here <= rooms.length && here >= 0) {
                        here -= 1;
                        currentLoc = rooms[here];
                    } else {
                        System.out.println("That is not a valid room number.");
                    }
                    // when room is changed show new current location
                    System.out.println("Current Location: " + currentLoc.getName());
                } else if (cmd.equalsIgnoreCase("l")) {
                    // when user enters l check if there is any items in room and display if there are 
                    if (currentLoc.getContents().size() == 0) {
                        System.out.println("No Items in Room.");
                    } else {
                        currentLoc.viewRoomContent();
                    }
                } else if (cmd.equalsIgnoreCase("t")) {
                    // when user enters l prompt for name of item they want to take
                    if (currentLoc.getnumberOfItems() == 0) {
                        // if theres nothing in the room
                        System.out.println("There's nothing in this room.");
                    } else {
                        // if there is items in the room
                        System.out.print("What do you want to take? ");
                        String takeThing = input.next();
                        ArrayList<Item> contents = currentLoc.getContents();
                        // check that user entered valid item name for item in room
                        for (int i = 0; i < currentLoc.getnumberOfItems(); i++) {
                            Item item = contents.get(i);
                            if (takeThing.equals(item.getName())) {
                                player.take(item);
                                currentLoc.dropItem(item);
                                System.out.println(takeThing + " added to inventory.");
                                break;
                            }
                        }
                    }
                } else if (cmd.equalsIgnoreCase("d")) {
                    //when user enters d prompt check if they have anything in inventory
                    if (player.getNumberOfObjects() == 0) {
                        // if inventory empty
                        System.out.println("You have nothing in your inventory.");
                    } else {
                        // if inventory is not empty
                        System.out.print("What do you want to drop? ");
                        String dropThing = input.next();
                        ArrayList<Item> inventory = player.getInventory();
                        // check if user entered valid item name from their inventory
                        for (int i = 0; i < player.getNumberOfObjects(); i++) {
                            Item item = inventory.get(i);
                            String name = item.getName();
                            if (name.equals(dropThing)) {
                                player.drop(item);
                                currentLoc.addItem(item);
                                System.out.println(name + " dropped.");
                                break;
                            }
                        }

                    }
                } else if (cmd.equalsIgnoreCase("u")) {
                    //when user enters u check if inventory has items in it and if those items can be used in this room 
                    if (player.getNumberOfObjects() == 0) {
                        // if inventory empty
                        System.out.println("You have nothing in your inventory to use.");
                    } else {
                        // if inventory is not empty
                        System.out.print("What do you want to use? ");
                        String useThing = input.next();
                        String roomName = currentLoc.getName();
                        ArrayList<Item> inventory = player.getInventory();
                        // check what item user want to use key or suit
                        if (useThing.equalsIgnoreCase("Key")) {
                            // if key
                            if (inventory.contains(item[1])) {
                                // check if user has key in their inventory.
                                // if user is using key in airlock room
                                if (roomName == "Airlock Room") {
                                    System.out.println("You opened the airlock door.");
                                    if (suitOn) {
                                        // you must put your spacesuit on befor you use key or you die.
                                        System.out.println("You've safely escaped!");
                                        System.out.println("GAME OVER: YOU WON!");
                                        System.exit(0);
                                    } else {
                                        System.out.println(
                                                "But you left without putting on your space suit. The vacuum of space pulled all the air out of your lungs, causing you to suffocate within minutes.");
                                        System.out.println("GAME OVER: YOU LOST :(");
                                    }
                                //if not in airlock room it can't be used
                                } else {
                                    System.out.println("You can't use the key in the " + roomName + ".");
                                }
                            } else {
                                // else you dont have the key
                                System.out.println("You don't have a key in your inventory.");
                            }
                        // if user is trying to use suit
                        } else if (useThing.equalsIgnoreCase("Suit")) {
                            // check if user has suit in inventory
                            if (inventory.contains(item[0])) {
                                suitOn = true;
                                System.out.println("You put your spacesuit on. Good Job!");
                                player.drop(item[0]);
                            } else if (suitOn = true) {
                                // if they already put it on
                                System.out.println("You already put the space suit on.");
                            } else {
                                // if they dont have suit in inventory and didn't already put it on
                                System.out.println("You don't have a space suit in your inventory.");
                            }
                        }
                    }
                } else if (cmd.equalsIgnoreCase("e")) {
                    // when user enters e display game over and end game
                    System.out.println("Giving up already?");
                    System.out.println("GAME OVER: YOU LOST :(");
                    System.exit(0);
                } else if (cmd.equalsIgnoreCase("vm")) {
                    // when user enters vm display rooms (name and description)
                    System.out.println("");
                    for (int i = 0; i < rooms.length; i++) {
                        System.out.println("#" + (i + 1) + " " + rooms[i].getName());
                        System.out.println(" - " + rooms[i].getDesc());
                    }
                    System.out.println("");
                } else if (cmd.equalsIgnoreCase("vi")) {
                    // when user enters vi display user's inventory
                    System.out.println("\nInventory: ");
                    if (player.getInventory().size() == 0) {
                        // if inventory is empty
                        System.out.println("No Items in Inventory.");
                    } else {
                        // if inventory is not empty
                        player.viewInventory();
                        System.out.println();
                    }
                } else {
                    // if user enters anything other than what they are allowed to enter above
                    System.out.println("Invalid command");
                }
            } while (run);
        }

    }
}
