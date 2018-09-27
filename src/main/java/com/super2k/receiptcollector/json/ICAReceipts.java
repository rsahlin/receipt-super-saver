package com.super2k.receiptcollector.json;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gson.annotations.SerializedName;
import com.super2k.receiptcollector.data.Item;
import com.super2k.receiptcollector.data.Receipt;
import com.super2k.receiptcollector.json.ICAReceiptRows.RowTransaction;

/**
 * Container for all receipts - each receipt is saved as a transaction carrying a transactionId that correlates
 * to an entry in the {@link ICAReceiptRows} object
 *
 */
public class ICAReceipts {

    public static final String TRANSACTION_HEADER = "TransactionHeader";

    public static class TransactionHeader {
        protected String customerId;
        protected ReceiptTransaction[] transactions;
    }

    public static class ReceiptTransaction {

        protected String receiptType;
        protected float totalDiscount;
        protected float transactionValue;
        protected String transactionId;
        protected Date transactionTimestamp;
        protected String marketingName;
        protected float vatAmount;
        protected String paymentType;

        public Date getTimeStamp() {
            return transactionTimestamp;
        }

        /**
         * Creates a new receipt using this class and list of rows.
         * 
         * @param items
         * @return
         * @throws IllegalArgumentException If items contains transactionId that does not match in this class
         */
        public Receipt createReceipt(List<RowTransaction> items) {
            List<Item> itemList = new ArrayList<>();
            for (RowTransaction rt : items) {
                Item item = rt.createItem();
                if (!item.getReceiptId().contentEquals(transactionId)) {
                    throw new IllegalArgumentException("Included transaction not within receipt, receiptId="
                            + transactionId + " row transactionId=" + item.getReceiptId());
                }
                itemList.add(item);
            }
            return new Receipt(receiptType, totalDiscount, transactionValue, transactionId, transactionTimestamp,
                    marketingName, vatAmount, paymentType, itemList);

        }

    }

    @SerializedName(TRANSACTION_HEADER)
    protected TransactionHeader transactionHeader;

    /**
     * Returns the total number of receipts
     * 
     * @return
     */
    protected int getReceiptCount() {
        return transactionHeader.transactions.length;
    }

    protected ReceiptTransaction[] getReceipts() {
        return transactionHeader.transactions;
    }

}
