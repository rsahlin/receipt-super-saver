package com.super2k.receiptcollector.data;

/**
 * A store object, this is the place where the receipt was recorded.
 *
 */
public class Store {

    /**
     * Store brand, if known. For instance ICA, Coop
     */
    protected String brand;
    /**
     * Name of specific store, eg 'ICA Nära Genarp' - normally unique name.
     */
    protected String name;

    /**
     * Creates a new store with the specified brand and name
     * 
     * @param brand
     * @param name
     */
    public Store(String brand, String name) {
        this.brand = brand;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Store) {
            return ((Store) o).name.contentEquals(name);
        }
        return false;
    }

}
