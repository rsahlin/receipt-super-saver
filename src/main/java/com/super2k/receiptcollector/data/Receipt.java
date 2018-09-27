package com.super2k.receiptcollector.data;

import java.util.Date;
import java.util.List;

/**
 * One receipt - agnostic non proprietary data
 *
 */
public class Receipt {

    protected String customerId;
    protected String receiptType;
    protected float totalDiscount;
    protected float transactionValue;
    protected String receiptId;
    protected Date transactionTimestamp;
    protected Store store;
    protected float vatAmount;
    protected String paymentType;

    protected Item[] items;

    public Receipt(String customerId, String receiptType, float totalDiscount, float transactionValue, String receiptId,
            Date transactionTimestamp, String storeName, float vatAmount,
            String paymentType, List<Item> items) {
        this.customerId = customerId;
        this.receiptType = receiptType;
        this.totalDiscount = totalDiscount;
        this.transactionValue = transactionValue;
        this.receiptId = receiptId;
        this.paymentType = paymentType;
        this.items = new Item[items.size()];
        this.transactionTimestamp = transactionTimestamp;
        this.store = new Store(storeName);
        this.vatAmount = vatAmount;
        for (int index = 0; index < this.items.length; index++) {
            this.items[index] = items.get(index);
        }

    }

}
