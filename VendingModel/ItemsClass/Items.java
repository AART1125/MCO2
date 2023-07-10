package VendingModel.ItemsClass;
/**
 * This is the <code>Items</code> Class. This is a representation of an item in the vending machine.
 * It contains the name, the item type, and the amount of calories it has.
 */

public class Items {
    private String name; // name of the item
    private String itemType; // type of the item
    private int calories; // amount of calories of the item

    /**
     * An <code>Items</code> class constructor. This is for initialization purposes to avoid a nullPointerException
     */
    public Items(){
        this.name = null;
        this.calories = 0;
        this.itemType = null;
    }

    /**
     * An <code>Items</code> class constructor that takes in the name, calories, and type of the item
     * @param name Name of the item
     * @param calories Calories of the item
     * @param type Item type
     */
    public Items(String name, int calories, String type){
        this.name = name;
        this.calories = calories;
        this.itemType = type;
    }

    /**
     * Gets the name of the <code>Items</code> instance
     * @return Item name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets the item type of the <code>Items</code> instance
     * @return Item type
     */
    public String getItemType() {
        return itemType;
    }

    /**
     * Gets the amount of calories of the <code>Items</code> instance
     * @return Calories
     */
    public int getCalories() {
        return this.calories;
    }

    /**
     * Sets the name of the <code>Items</code> instance
     * @param name New name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the amount of calories of the <code>Items</code> instance
     * @param calories New calories
     */
    public void setCalories(int calories) {
        this.calories = calories;
    }

    /**
     * Sets the item type of the <code>Items</code> instance
     * @param itemType New item type
     */
    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

}
