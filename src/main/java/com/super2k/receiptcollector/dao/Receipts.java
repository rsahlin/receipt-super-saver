package com.super2k.receiptcollector.dao;

import java.util.List;

import com.super2k.receiptcollector.data.Receipt;

/**
 * Abstraction for a list of Receipt.
 * This is used in DAO methods.
 *
 */
public interface Receipts {

    /**
     * Adds the receipt to list of receipts
     * 
     * @param receipt
     */
    public void addReceipt(Receipt receipt);

    /**
     * Returns a list with all receipts
     * 
     * @return
     */
    public List<Receipt> getReceipts();

    /**
     * Returns the number of receipts that will be returned by calling {@link #getReceipts()}
     * 
     * @return
     */
    public int getCount();

    /**
     * Returns the total cost for all receipts
     * 
     * @return
     */
    public float getTotalPrice();

}
