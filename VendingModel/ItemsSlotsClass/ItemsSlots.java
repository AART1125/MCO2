package VendingModel.ItemsSlotsClass;

import VendingModel.ItemsClass.Items;

/**
 * This is an <code>ItemSlots</code> class. This represents the different slots on the vending machine.
 * It contains the quantity of items, the price, the label of the slot, and the items in it.
 */
public class ItemsSlots{
    private static final int MAXITEMS = 20;// Maximum amount of items

    private int quantity; // Current amount of items in the slot
    private double price; // Price of the item
    private final String label;// Label of the slots
    private Items[] productItems = new Items[MAXITEMS]; // Array of <code>Items</code> instances

    /**
     * This is an <code>ItemSlots</code> class constructor. This is for initialization purposes and the creation of labels
     * @param row Row of the Item slot
     * @param col Collumn of the Item slot
     */
    public ItemsSlots(int row, int col){
        this.quantity = 0;
        this.price = 0.0;
        this.label = setLabel(row, col);
    }

    /**
     * A method that replenishes the amount of items based on a given input
     * @param itemsArr Array of items
     * @param input Amount to be added
     */
    public void replenishItems(Items[] itemsArr, int input){
        Items tempItems = itemsArr[0];
        int origQuantity = this.getQuantity(), i;

        this.quantity += input;

        for(i = origQuantity - 1; i < this.quantity; i++){
            itemsArr[i] = tempItems;
        }
    }

    /**
     * Removes a certain amount of items in the given slot
     * @param itemArr Array of items
     * @param origQuantity Original quantity of the items
     */
    public void decreaseItemsFromSlot(Items[] itemArr, int origQuantity){
        int i;
        Items[] newArr = new Items[MAXITEMS];

        for(i = 0; i < this.quantity; i++){
            newArr[i] = new Items(itemArr[i].getName(), itemArr[i].getCalories(), itemArr[i].getItemType());
        }

        this.setProductItems(newArr);
    }

    /**
     * Decreases the quantity of items in the slot
     * @param decrease quantity to decreast
     */
    public void decreaseQuantity(int decrease){
        this.quantity -= decrease;
    }

    /**
     * Gets the price of the item in item slot
     * @return price
     */
    public double getPrice() {
        return this.price;
    }

    /**
     * Gets the quantity of items in the slot
     * @return quantity
     */
    public int getQuantity() {
        return this.quantity;
    }

    /**
     * Gets the label assigned to the slot
     * @return label
     */
    public String getLabel() {
        return this.label;
    }

    /**
     * Gets the item array of the slot
     * @return items array
     */
    public Items[] getProductItem() {
        return this.productItems;
    }

    /**
     * Sets the new quantity of the item slot 
     * @param quantity new quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Sets the new item available in the slot
     * @param productItems new items
     */
    public void setProductItems(Items productItems) {
        int i;
        for (i = 0; i < this.quantity; i++) {
            this.productItems[i] = new Items(productItems.getName(), productItems.getCalories(), productItems.getItemType());
        }
    }

    /**
     * Sets the price of the items in the slot
     * @param price new price
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Sets the product items array in the slot
     * @param productItems new product items
     */
    public void setProductItems(Items[] productItems) {
        this.productItems = productItems;
    }

    /**
     * Sets the label based on row and collumn
     * @param row - row index of the <code>Items Slots</code> array
     * @param col - collumn index of the <code>Items Slots</code> array
     * @return new label
     */
    public String setLabel(int row, int col){
        String label = null;

        switch(row+1){
            case 1: label = "A"; break;
            case 2: label = "B"; break;
            case 3: label = "C"; break;
            case 4: label = "D"; break;
            case 5: label = "E"; break;
            case 6: label = "F"; break;
        }

        switch(col+1){
            case 1: label += "1"; break;
            case 2: label += "2"; break;
            case 3: label += "3"; break;
            case 4: label += "4"; break;
            case 5: label += "5"; break;
        }

        return label;
    }
    
}