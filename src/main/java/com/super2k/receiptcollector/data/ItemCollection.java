package com.super2k.receiptcollector.data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * A number of Items where the quantity and price is added together.
 *
 */
public class ItemCollection implements Comparable<ItemCollection> {

    /**
     * The total quantity.
     */
    protected float totalQuantity;
    /**
     * The total price
     */
    protected float totalPrice;

    /**
     * The total discount, a value of 1 means the price was reduced by 1
     */
    protected float totalDiscount;

    /**
     * The item names
     */
    protected Set<String> itemDescriptions = new HashSet<>();

    /**
     * The items for this collection
     */
    protected List<Item> items;

    public ItemCollection(List<Item> items) {
        this.items = new ArrayList<>();
        for (Item i : items) {
            this.items.add(i);
            totalPrice += i.price;
            totalDiscount += i.discountValue;
            totalQuantity += i.quantity;
            itemDescriptions.add(i.itemDesc);
        }
    }

    @Override
    public String toString() {
        String result = "Total price: " + (totalPrice - totalDiscount) + " for items ";
        for (String s : itemDescriptions) {
            result += " '" + s + "' ";
        }
        return result + ", Total quantity: " + totalQuantity + " discount " + totalDiscount;
    }

    @Override
    public int compareTo(ItemCollection o) {
        return (int) ((totalPrice - totalDiscount) - (o.totalPrice - o.totalDiscount));
    }

    public float getTotalQuantity() {
        return totalQuantity;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public float getTotalDiscount() {
        return totalDiscount;
    }

    public Set<String> getItemDescriptions() {
        return itemDescriptions;
    }

    public int getItemDescriptionCount() {
        return itemDescriptions.size();
    }

    public List<Item> getItems() {
        return items;
    }

}
