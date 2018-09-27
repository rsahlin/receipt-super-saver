package com.super2k.receiptcollector.json;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import com.google.gson.annotations.SerializedName;
import com.super2k.receiptcollector.data.Item;
import com.super2k.receiptcollector.json.ICAReceipts.ReceiptTransaction;

/**
 * JSON definition of ICA receipt rows, this is the top level document for all rows in all receipts.
 *
 */
public class ICAReceiptRows {

    public static final String LINE_ITEMS = "LineItems";

    public static class LineItems {
        protected String responseType;
        protected String customerId;
        protected RowTransaction[] transactions;
    }

    public class RowTransaction {

        protected float quantity;
        protected float price;
        protected String personalOfferId;
        protected float voucherValue;
        protected float discountQuantity;
        protected String slipId;
        protected String itemDesc;
        protected float discountValue;
        protected String transactionId;

        public float getQuantity() {
            return quantity;
        }

        public float getPrice() {
            return price;
        }

        public Item createItem() {
            return new Item(quantity, price, personalOfferId, voucherValue, discountQuantity, slipId, itemDesc,
                    -discountValue, transactionId);
        }

    }

    @SerializedName(LINE_ITEMS)
    LineItems lineItems;

    /**
     * Since we are using just one thread Hashtable is ok - otherwise use HashMap.
     */
    transient Hashtable<String, List<RowTransaction>> transactions;

    protected void init() {
        if (transactions == null) {
            transactions = new Hashtable<>();
            sortByTransactionId(transactions);
        }
    }

    /**
     * Sort rows by transaction id
     * 
     * @param transactions
     */
    protected void sortByTransactionId(Hashtable<String, List<RowTransaction>> transactions) {
        for (RowTransaction row : lineItems.transactions) {
            List<RowTransaction> rows = transactions.get(row.transactionId);
            if (rows == null) {
                rows = new ArrayList<RowTransaction>();
                transactions.put(row.transactionId, rows);
            }
            rows.add(row);
        }
        System.out.println("Sorted rows by transaction: " + transactions.size() + " transactionIds");
    }

    /**
     * Adds all rows matching the receipt id to the result array.
     * 
     * @param receipt
     * @param result
     */
    protected void findRows(ReceiptTransaction receipt, List<RowTransaction> result) {
        init();
        List<RowTransaction> rows = transactions.get(receipt.transactionId);
        if (rows != null) {
            result.addAll(rows);
        }
    }

}
