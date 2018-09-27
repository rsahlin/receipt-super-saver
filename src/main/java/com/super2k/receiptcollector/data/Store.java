package com.super2k.receiptcollector.data;

/**
 * A store object, this is the place where the receipt was recorded.
 *
 */
public class Store {

    protected String name;

    public Store(String name) {
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
