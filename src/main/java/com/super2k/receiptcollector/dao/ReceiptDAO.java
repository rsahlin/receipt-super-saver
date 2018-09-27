package com.super2k.receiptcollector.dao;

import java.util.Date;
import java.util.List;

import com.super2k.receiptcollector.data.ItemCollection;

/**
 * Access abstraction for sorting/searching receipt data
 * This is used to search receipt data in a non proprietary way.
 *
 */
public interface ReceiptDAO {

    public class SimpleDate {
        public final int year;
        public final int month;
        public final int day;

        public SimpleDate(int year, int month, int day) {
            this.year = year;
            this.month = month;
            this.day = day;
        }

    }

    /**
     * Returns a list with receipts within the specified dates
     * 
     * @param receipts
     * @param begin
     * @param end
     * @return
     */
    public Receipts findByDate(Receipts receipts, SimpleDate begin, SimpleDate end);

    /**
     * Returns a list with receipts within the specified dates
     * 
     * @param receipts
     * @param begin
     * @param end
     * @return
     */
    public Receipts findByDate(Receipts receipts, Date begin, Date end);

    /**
     * Returns a list with items added together by item description (name), ordered by total item price
     * 
     * @param receipts
     * @return
     */
    public List<ItemCollection> sortByItemsPrice(Receipts receipts);

}
