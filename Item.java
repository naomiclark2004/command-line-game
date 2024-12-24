public class Item {
    private String name;
    private String desc;

    // item has two fields
    // name stores the name of each item
    // desc stores a short description of each item

    public Item(String name, String desc) {
        this.name = name;
        this.desc = desc;
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

    public String look() {
        return getDesc();
    }

    // for when .get() is used like contents.get() or inventory.get()
    // displays both name and description of item
    
    public String toString() {
        return name + " - " + desc;
    }

}
